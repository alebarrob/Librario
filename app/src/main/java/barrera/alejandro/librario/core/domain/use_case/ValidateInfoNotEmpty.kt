package barrera.alejandro.librario.core.domain.use_case

class ValidateInfoNotEmpty {
    operator fun invoke(info: List<String>): Result {
        info.forEach { if (it.isEmpty()) return Result.Error }
        return Result.Success
    }

    sealed class Result {
        object Success: Result()
        object Error: Result()
    }
}