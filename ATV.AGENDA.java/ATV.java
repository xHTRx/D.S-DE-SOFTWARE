/*Crie um programa em Java com as seguintes especificações.

1. Classe Usuario com:

- Atributos: String nome, int idade, String email.

- Construtor com validações: nome com menos de 3 caracteres, idade fora da faixa 18-99 e e-mail sem @.



2. Classe principal (main) que:

- Tenta cadastrar 1 ou mais usuários.

- Usa try-catch para tratar os erros e mostrar mensagens personalizadas.



Exemplos de saída:

Erro no cadastro: o nome deve conter pelo menos 3 caracteres.


Erro no cadastro: A idade deve estar entre 18 e 99 anos.


Erro no cadastro: O email deve conter o caractere '@'.


Usuario cadastrado: Joao (19 anos, usuario@email.com)*/





class ATV{

    public static void main(String[] args){

        try{

            Usuario usuario = new Usuario("ht", 17, "htrgmail.com");
            usuario.exibirInformacoes();

        } catch(IllegalArgumentException e){

            System.out.println("Erro(s) no cadastro do usuario: ");
            System.out.println(e.getMessage());

        } finally{
            System.out.println("Processo finalizado!!");
        }

    }




}


class Usuario{

    private String nome;
    private int idade;
    private String email;

    public Usuario(String nome, int idade, String email){

        StringBuilder erros = new StringBuilder();

        if(nome.length() < 3){
            erros.append("\nNome precisa ter pelo menos 3 letras!!!\n");
        }

        if(idade < 18 || idade > 99){
            erros.append("\nIdade precisa estar entre 18 e 99 anos!!!\n");
        }

        if(!email.contains("@")){
            erros.append("\nNecessaria a utilização de '@' em seu email!!!\n");
        }

        if(erros.length() > 0 ){
            throw new IllegalArgumentException(erros.toString());
        }

        this.nome = nome;
        this.idade = idade;
        this.email = email;

    }


    public void exibirInformacoes(){

        System.out.println("\nNome de Usuario: " + nome);
        System.out.println("\nIdade do Usuario: " + idade);
        System.out.println("\nE-mail do Usuario: " + email);


    }





}