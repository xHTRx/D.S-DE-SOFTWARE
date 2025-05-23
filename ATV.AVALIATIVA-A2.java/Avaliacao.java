class Avaliacao {
    public static void main(String[] args) {
 
 
        Pessoa pessoa = new Pessoa("htr", 18);
        Pessoa pessoa2 = new Pessoa("DEUS", 9999);
 
 
        Curso curso = new Curso("ADS", 230);
        Curso curso2 = new Curso("Ingles", 78);
 
 
        Aluno aluno = new Aluno(pessoa, "A001", 3.0, 9.0, curso);
        Aluno aluno2 = new Aluno(pessoa2,"B002", 6.0, 8.0, curso2);
 
 
 
        System.out.println("\n\n--- Informacoes alunos matriculados: ---");
        aluno.exibirInformacoes();
        aluno2.exibirInformacoes();
 
 
        System.out.println("\n\n--- Informacoes cursos: ---");
 
 
        curso.exibirInformacoes();
        curso2.exibirInformacoes();
       
    }
}
 
class Pessoa {
    private String nome;
    private int idade;
    
 
    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    
    }
 
    public void exibirInformacoes() {
        System.out.println("\n\nNome: " + nome);
        System.out.println("Idade: " + idade);
        
    }
 
 
    public String getNome(){
        return nome;
    }
 
 
    public void setNome(String nome){
        this.nome = nome;
    }
 
 
    public int getIdade(){
        return idade;
    }
 
 
    public void setIdade(int idade){
        this.idade = idade;
    }
 
 
}
 
 
class Curso{
 
 
    private String nomeCurso;
    private int cargaHoraria;
 
 
  public Curso(String nomeCurso, int cargaHoraria){
    this.nomeCurso = nomeCurso;
    this.cargaHoraria = cargaHoraria;
 
 
  }
 
 
 
  public void exibirInformacoes() {
    System.out.println("\nCurso: " + nomeCurso);
    System.out.println("Carga horaria: " + cargaHoraria + "h\n");
}
 
 
 
    public String getNomeCurso(){
        return nomeCurso;
    }
 
 
    public void setNomeCurso(String nomeCurso){
        this.nomeCurso = nomeCurso;
    }
 
 
    public int getCargaHoraria(){
        return cargaHoraria;
    }
 
 
    public void setcargaHoraria(int cargaHoraria){
        this.cargaHoraria = cargaHoraria;
    }
 
 
}
 
 
class Aluno{
 
 
    private Pessoa pessoa;
    private String matricula;
    private double nota1;
    private double nota2;
    private Curso curso;
 
 
    public Aluno(Pessoa pessoa, String matricula, double nota1, double nota2, Curso curso){
 
 
        this.matricula = matricula;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.pessoa = pessoa;
        this.curso = curso;
    }
 
 
 
 
    
    public void exibirInformacoes(){
 
 
        double media = (nota1 + nota2) / 2;
 
 
        System.out.println("\n\nNome: " + pessoa.getNome());
        System.out.println("Idade: " + pessoa.getIdade());
        System.out.println("Matricula: " + matricula);
        System.out.println("Curso: " + curso.getNomeCurso());
        System.out.println("Carga Horaria: " + curso.getCargaHoraria() + "h");
        System.out.println("Media: " + media + (( media>= 7.0? "\nSituacao: Aprovado" : "\nSituacao: Reprovado")));
 
 
    }
    }