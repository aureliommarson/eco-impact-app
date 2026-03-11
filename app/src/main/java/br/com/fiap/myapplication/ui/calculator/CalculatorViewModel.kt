package br.com.fiap.myapplication.ui.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.myapplication.data.repository.CarbonRepository
import kotlinx.coroutines.launch

/*
 * ViewModel responsável pela lógica da tela de cálculo.
 * Ele recebe os dados da interface, chama o repositório
 * para fazer o cálculo e devolve o resultado para a tela.
 */
class CalculatorViewModel : ViewModel() {

    // Instância do repositório que faz a comunicação com a API
    private val repository = CarbonRepository()

    // LiveData interno que armazena o resultado do cálculo
    private val _estimateResult = MutableLiveData<ResultState<Double>>()

    // LiveData público observado pela interface
    // A tela pode ler o valor, mas não pode alterar
    val estimateResult: LiveData<ResultState<Double>> = _estimateResult

    /*
     * Função chamada quando o usuário solicita o cálculo
     * Recebe o consumo de energia em kWh
     */
    fun calculate(kwh: Double) {

        // Define o estado como carregando
        // A interface pode usar isso para mostrar um indicador de carregamento
        _estimateResult.value = ResultState.Loading

        // Executa a chamada da API em segundo plano
        viewModelScope.launch {

            // Solicita ao repositório o cálculo da emissão de carbono
            val result = repository.getCarbonEstimate(kwh)

            // Atualiza o resultado observado pela interface
            _estimateResult.value = result
        }
    }
}