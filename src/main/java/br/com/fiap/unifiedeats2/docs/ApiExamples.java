package br.com.fiap.unifiedeats2.docs;

public final class ApiExamples {

    private ApiExamples() {
    }

    // =========================
    // USUÁRIO
    // =========================
    public static final String USUARIO_REQUEST = """
            {
              "nome": "João Silva",
              "email": "joao@email.com",
              "login": "joaosilva",
              "senha": "123456",
              "endereco": {
                "cep": "03450000",
                "logradouro": "Rua A",
                "numero": "100",
                "complemento": "Casa",
                "bairro": "Vila Carrão",
                "cidade": "São Paulo",
                "estado": "SP"
              },
              "tipoUsuarios": [
                {
                  "id": 1
                }
              ]
            }
            """;

    // =========================
    // TIPO USUÁRIO
    // =========================
    public static final String TIPO_USUARIO_REQUEST = """
            {
              "nome": "CLIENTE"
            }
            """;

    // =========================
    // RESTAURANTE
    // =========================
    public static final String RESTAURANTE_REQUEST = """
            {
              "nome": "Restaurante A",
              "endereco": {
                "cep": "03450000",
                "logradouro": "Rua Restaurante",
                "numero": "10",
                "complemento": "Loja",
                "bairro": "Vila Carrão",
                "cidade": "São Paulo",
                "estado": "SP"
              },
              "tipoCozinha": "Brasileira",
              "horarioFuncionamento": "09:00 às 22:00",
              "donoId": 1
            }
            """;

    // =========================
    // ITEM CARDÁPIO (CADASTRO)
    // =========================
    public static final String CADASTRAR_ITEM_CARDAPIO_REQUEST = """
            {
              "nome": "Prato Executivo",
              "descricao": "Arroz, feijão e bife",
              "preco": 29.90,
              "disponivelApenasNoLocal": false,
              "foto": "/imagens/prato.jpg",
              "restauranteId": 1
            }
            """;

    // =========================
    // ITEM CARDÁPIO (ATUALIZAÇÃO)
    // =========================
    public static final String ATUALIZAR_ITEM_CARDAPIO_REQUEST = """
            {
              "nome": "Prato Executivo Premium",
              "descricao": "Arroz, feijão, picanha e fritas",
              "preco": 39.90,
              "disponivelApenasNoLocal": true,
              "foto": "/imagens/prato-premium.jpg",
              "restauranteId": 1
            }
            """;

    // =========================
    // ERROS PADRÃO
    // =========================
    public static final String ERRO_VALIDACAO = """
            {
              "type": "/errors/validacao",
              "title": "Erro de validação",
              "status": 400,
              "detail": "Um ou mais campos estão inválidos.",
              "instance": "/v1/recurso"
            }
            """;

    public static final String ERRO_RECURSO_NAO_ENCONTRADO = """
            {
              "type": "/errors/recurso-nao-encontrado",
              "title": "Recurso não encontrado",
              "status": 404,
              "detail": "Registro não encontrado.",
              "instance": "/v1/recurso/1"
            }
            """;

    // =========================
    // LOGIN
    // =========================
    public static final String LOGIN_REQUEST = """
            {
              "login": "joaosilva",
              "senha": "123456"
            }
            """;

    public static final String LOGIN_RESPONSE = """
            {
              "id": 1,
              "nome": "João Silva",
              "email": "joao@email.com",
              "login": "joaosilva"
            }
            """;

    public static final String ERRO_NAO_AUTORIZADO = """
            {
              "type": "/errors/credenciais-invalidas",
              "title": "Credenciais inválidas",
              "status": 401,
              "detail": "Login ou senha inválidos.",
              "instance": "/v1/autenticacao/login"
            }
            """;
}