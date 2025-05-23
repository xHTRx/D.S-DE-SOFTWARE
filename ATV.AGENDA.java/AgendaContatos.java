/*Conteúdo do teste

1 - Altere a listagem de contatos para que seja exibido, dinamicamente, o número do contato. Exemplo:


N.    Nome                 Telefone       
1     Marcelo              1111-1111         
2     Pedro                2222-2222

2 - Altere a inclusão dos contatos para que o nome e o telefone sejam entradas do usuário. Exemplo:


Digite o nome do contato: Marcelo
Digite o telefone do contato: 1111-1111
﻿Contato adicionado: Marcelo

3 - A edição do telefone de um contato deve ser baseada no número atribuído ao mesmo na listagem. Exemplo:


Digite o número do contato a ser editado: 1
Digite o novo telefone para Marcelo: 1234-5678
Telefone de Marcelo atualizado para 1234-5678﻿﻿


4 - A exclusão de um contato deve ser baseada no número atribuído ao mesmo na listagem. Exemplo:


Digite o número do contato a ser excluído: 1
Contato removido com sucesso.


5 - Código fonte base:*/


import java.io.IOException;
import java.nio.file.*;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
 
public class AgendaContatos {
 
    private static final Path CAMINHO_ARQUIVO = Paths.get("agenda.txt"); /* Caminho constante para o arquivo */
    private static final Scanner scanner = new Scanner(System.in); /* Scanner para ler a entrada do usuário */
 
    public static void main(String[] args) {
        int opcao;
        int NumBusca;
        boolean listagemFeita = false;
        
        do {
            /* Exibe o menu e lê a opção do usuário */
            System.out.println("\n-- Agenda de contatos --\n");
            System.out.println("[1] Adicionar contato");
            System.out.println("[2] Editar contato");
            System.out.println("[3] Excluir contato");
            System.out.println("[4] Listar contatos");
            System.out.println("[5] Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); /* Consumir a nova linha após a leitura do número */
 
 
 
            
            String nome;
            String telefone;
            
            switch (opcao) {
                case 1:
                    /* Adicionando um novo contato */
 
 
                    System.out.print("Digite o nome do contato:");
                    nome = scanner.nextLine();
                    System.out.print("Digite o telefone do contato:");
                    telefone = scanner.nextLine();          
                    adicionarContato (nome, telefone);
                    break;
 
 
 
 
                case 2:
                    /* Editando um contato */
 
 
                    if(!listagemFeita){
                        System.out.println("\nRealize uma listagem antes de editar um contato!");
                        break;
                    }
 
 
                    System.out.print("Digite o numero de ID do contato a ser editado: ");
                    NumBusca = scanner.nextInt();
                    scanner.nextLine(); // <-- limpa o buffer
                    listagemFeita = false;
 
 
                    editarContato(NumBusca);
                    break;
 
 
 
 
                case 3:
                    /* Excluindo um contato */
 
 
                    if(!listagemFeita){
                        System.out.println("\nRealize uma listagem antes de excluir um contato!");
                        break;
                    }
 
 
                    System.out.print("Digite o numero de ID do contato a ser excluido:");
                    NumBusca = scanner.nextInt();
 
 
                    listagemFeita = false;
 
 
                    excluirContato(NumBusca);
                    break;
 
 
 
 
                case 4:
                    /* Listando todos os contatos */
                     listagemFeita = true;
                    listarContatos();
                    
                    break;
 
 
 
 
                case 5:
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }
 
 
 
 
 
 
 
 
 
    private static void adicionarContato(String nome, String telefone) {
 
 
        if (nome.isBlank() || telefone.isBlank()) 
        {
    System.out.println("Nome ou telefone não pode estar vazio.");
    return;
        }
 
        String linha = nome + ";" + telefone; /* Formata os dados */
 
        try {
            /* Escreve a linha no arquivo:
               CREATE: cria o arquivo se não existir
               APPEND: adiciona ao final do arquivo
               System.lineSeparator(): adiciona quebra de linha correta para o sistema operacional */
 
 
            Files.write(CAMINHO_ARQUIVO,
                        (linha + System.lineSeparator()).getBytes(),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
            System.out.println("Contato adicionado: " + nome);
        } catch (IOException e) {
            System.out.println("Erro ao adicionar contato: " + e.getMessage());
        }
    }
 
 
 
 
 
 
 
 
    private static void editarContato(int NumBuscar) {
 
 
        try {
            if (!Files.exists(CAMINHO_ARQUIVO)) {
                System.out.println("\nArquivo ainda nao existe.");
                return;
            }
 
 
            List<String> linhas = Files.readAllLines(CAMINHO_ARQUIVO);
 
                if (NumBuscar < 1 || NumBuscar > linhas.size()){
                    System.out.println("\nNumero inválido ou inexistente!");
                    return;
                } else{
 
 
                String[] partes = linhas.get(NumBuscar - 1).split(";");
                String nome = partes[0];
                
                System.out.print("Digite o novo telefone para " + nome + ": ");
                String novoTelefone = scanner.nextLine();
                
 
 
 
                linhas.set(NumBuscar - 1, nome + ";" + novoTelefone); /* Substitui o telefone */
 
 
                Files.write(CAMINHO_ARQUIVO, linhas, StandardOpenOption.TRUNCATE_EXISTING);
                System.out.println("Telefone de " + nome + " atualizado para " + novoTelefone);
                }
 
 
            } catch (IOException e) {
            System.out.println("Erro ao editar contato: " + e.getMessage());
        }
    }
    
 
 
 
 
 
 
 
 
    private static void excluirContato(int NumBusca) {
 
        try {
            if (!Files.exists(CAMINHO_ARQUIVO)) {
                System.out.println("Arquivo ainda nao existe ou nao ha contatos na agenda.");
                return;
            }
 
            List<String> linhas = Files.readAllLines(CAMINHO_ARQUIVO);
            boolean encontrado = false;
 
            /* Itera sobre a lista e remove o contato com o nome correspondente */
            int i = 0;
 
 
            for (i = 0; i < linhas.size(); i++) {
 
 
                if (NumBusca == i +1) {
                    linhas.remove(i); /* Remove o contato */
 
 
                    encontrado = true;
                    break; /* Interrompe quando o contato é encontrado */
                }
            }
 
            if (encontrado) {
                /* Sobrescreve o arquivo com os dados atualizados */
                Files.write(CAMINHO_ARQUIVO, linhas, StandardOpenOption.TRUNCATE_EXISTING);
                System.out.println("Contato removido: " + (i + 1));
            } else {
                System.out.println("Contato " + (i + 1) + " nao encontrado.");
            }
 
        } catch (IOException e) {
            System.out.println("Erro ao excluir contato: " + e.getMessage());
        }
    }
    
 
 
   
 
 
 
 
 
 
 
 
 
    private static void listarContatos() {
        if (!Files.exists(CAMINHO_ARQUIVO)) {
            System.out.println("Arquivo ainda nao existe.");
            return;
        }
 
 
 
        try {
            List<String> linhas = Files.readAllLines(CAMINHO_ARQUIVO);
 
            /* Verifica se há contatos */
            if (linhas.isEmpty()) {
                System.out.println("Nenhum contato salvo.");
            } else {
                /* Ordena os contatos alfabeticamente */
                Collections.sort(linhas);
 
                /* Exibe os títulos e os contatos formatados */
                System.out.println("\n-- Listagem de Contatos --\n");
 
                System.out.printf("%-10s %-20s %-15s\n", "N.", "Nome", "Telefone");
 
                for (int i = 0; i < linhas.size(); i++) {
                    
                   String[] partes = linhas.get(i).split(";");
                    if (partes.length >= 2) {
                        System.out.printf("%-10s %-20s %-15s\n", i + 1, partes[0], partes[1]);
                    } else {
                        System.out.printf("%-10s %-20s %-15s\n", i + 1, linhas.get(i), "Telefone ausente");
                    }
                }
 
                /* Sobrescreve o arquivo com os dados ordenados */
                Files.write(CAMINHO_ARQUIVO, linhas, StandardOpenOption.TRUNCATE_EXISTING);
            
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler contatos: " + e.getMessage());
        }
    }
}