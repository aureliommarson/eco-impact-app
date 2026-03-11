package br.com.fiap.myapplication.ui.calculator

/*
 * Classe usada para representar os diferentes estados
 * de uma operação no aplicativo, como carregando,
 * sucesso ou erro.
 */
sealed class ResultState<out T> {

    // Estado de sucesso
    // Guarda os dados retornados pela operação
    data class Success<out T>(val data: T) : ResultState<T>()

    // Estado de erro
    // Guarda a mensagem de erro que pode ser mostrada ao usuário
    data class Error(val message: String) : ResultState<Nothing>()

    // Estado de carregamento
    // Indica que a operação ainda está sendo executada
    object Loading : ResultState<Nothing>()
}