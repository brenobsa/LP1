> Exercicio 1 - Altere o programa para imprimir uma mensagem diferente.

```
public class MyFirstJavaProgram {

   public static void main(String []args) {
      System.out.println("Testandooo!");
   }
}
```

>---

> Exercicio 2 - Altere o programa para imprimir duas linhas de texto usando duas linhas de código System.out.

```
public class MyFirstJavaProgram {
   public static void main(String []args) {
      System.out.println("Testandooo!"); 
      System.out.println("Teste Número 2");
   }
}

```

---

> Exercicio 3 - Sabendo que os caracteres \n representam uma quebra de linhas, imprima duas linhas de texto usando uma única linha de código System.out


```
public class MyFirstJavaProgram {
   public static void main(String []args) {
      System.out.println("Primeira Linha \nSegunda linha"); 
      
   }
}

```

> Exercicio 4 - Na empresa em que trabalhamos, há tabelas com o gasto de cada mês. Para fechar o balanço do primeiro trimestre, precisamos somar o gasto total. Sabendo que, em janeiro, foram gastos 15 mil reais, em fevereiro, 23 mil reais e, em março, 17 mil reais, faça um programa que calcule e imprima a despesa total no trimestre e a média mensal de gastos. 
```
public class MyFirstJavaProgram {
   public static void main(String []args) {
      
      
      int janeiro = 15000;
      int fevereiro = 23000;
      int marco = 17000;
      int totalmeses = janeiro + fevereiro + marco;
      System.out.println("Valores: \n-Janeiro: "+janeiro+"\n-Fevereiro: "+fevereiro+"\n-Marco: "+marco);
      System.out.println("Valor Total: " + totalmeses);
      
   }
}

```
> Exercicio 5 - Programa que leia as notas e calcule a média de LP1 deste semestre, referente a um determinado aluno.

```
public class Faculdade {
   public static void main(String []args) {
      
      double nota1 = 8.5;
      double nota2 = 7;
      double nota3 = 6;
      
      double media =  (nota1 + nota2 + nota3)/3;

      System.out.printf("Media final: %.2f%n", media);
      
   }
}

```

> Exercicio 6 

AppSistema.java

```
package org.example.fateclp;

class Feira {
    String produto = "Manga";
    double preco = 5.50;
    int estoque = 100;

    void vender() { if (estoque > 0) estoque--; }
    void aplicarDesconto() { preco -= 1.0; }
    void resetarPreco() { preco = 5.50; }
}

class Salao {
    String servico = "Corte";
    double valor = 45.0; // Atributo: Valor do corte
    boolean aberto = true;

    void agendar() { System.out.println("Agendamento realizado!"); }
    void realizarServico() { System.out.println("Realizando o serviço: " + servico); }
    void alternarStatus() { aberto = !aberto; }
}

class Farmacia {
    String remedio = "Aspirina";
    int unidades = 60;
    boolean receitaValidada = false; // Atributo: Status da receita

    void venderMedicamento() { if (unidades > 0) unidades--; }
    void validarReceita() { receitaValidada = true; }
    void reporEstoque() { unidades += 20; }
}

```

Modelos.java

```
package org.example.fateclp;

class Feira {
    String produto = "Manga";
    double preco = 5.50;
    int estoque = 100;

    void vender() { if (estoque > 0) estoque--; }
    void aplicarDesconto() { preco -= 1.0; }
    void resetarPreco() { preco = 5.50; }
}

class Salao {
    String servico = "Corte";
    double valor = 45.0; // Atributo: Valor do corte
    boolean aberto = true;

    void agendar() { System.out.println("Agendamento realizado!"); }
    void realizarServico() { System.out.println("Realizando o serviço: " + servico); }
    void alternarStatus() { aberto = !aberto; }
}

class Farmacia {
    String remedio = "DorFlex";
    int unidades = 60;
    boolean receitaValidada = false; // Atributo: Status da receita

    void venderMedicamento() { if (unidades > 0) unidades--; }
    void validarReceita() { receitaValidada = true; }
    void reporEstoque() { unidades += 20; }
}

```
 
