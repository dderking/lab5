package validator;

public class Validator {
	public static void verificaStringVazia(String valor, String mensagem) {
		if (valor.trim().isEmpty()) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	public static void verificaStringNull(String valor, String mensagem) {
		if (valor == null) {
			throw new NullPointerException(mensagem);
		}
	}

	public static void verificaCpfInvalido(String valor, String mensagem) {
		if (valor.length() != 11) {
			throw new IllegalArgumentException(mensagem);
		}

	}

	public static void verificaDoubleValido(double valor, String mensagem) {
		if (valor < 0) {
			throw new NullPointerException(mensagem);
		}
	}
	public static void verificaDataValida(String valor, String mensagem) {
		if (valor.length()!=10) {
			throw new IllegalArgumentException(mensagem);
		}
	}
}
