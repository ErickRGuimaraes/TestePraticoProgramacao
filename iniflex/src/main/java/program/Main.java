package program;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import entities.Funcionario;

public class Main {
    public static void main(String[] args) {
        // 3.1 - Inserir todos os funcionários
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.64"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // 3.2 - Remover o funcionário "João"
        funcionarios.removeIf(f -> f.getNome().equals("João"));
        
        // 3.3 - Imprimir todos os funcionários com informações formatadas
        System.out.println("--- 3.3 - Todos os Funcionários ---");
        funcionarios.forEach(f -> System.out.printf("Nome: %s, Data de Nascimento: %s, Salário: R$ %s, Função: %s%n",
                f.getNome(), f.getDataNascimentoFormatada(), f.getSalarioFormatado(), f.getFuncao()));

        // 3.4 - Aumento de 10% nos salários
        funcionarios.forEach(f -> {
            BigDecimal novoSalario = f.getSalario().multiply(new BigDecimal("1.10"));
            f.setSalario(novoSalario);
        });
        System.out.println("\n--- 3.4 - Salários atualizados com 10% de aumento ---");
        funcionarios.forEach(f -> System.out.printf("Nome: %s, Novo Salário: R$ %s%n",
                f.getNome(), f.getSalarioFormatado()));

        // 3.5 & 3.6 - Agrupar e imprimir funcionários por função
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
        System.out.println("\n--- 3.6 - Funcionários agrupados por função ---");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(f -> System.out.printf("  - %s%n", f.getNome()));
        });
        
        // 3.8 - Imprimir funcionários que fazem aniversário nos meses 10 e 12
        System.out.println("\n--- 3.8 - Aniversariantes de Outubro e Dezembro ---");
        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
                .forEach(f -> System.out.printf("Nome: %s, Data de Aniversário: %s%n", f.getNome(), f.getDataNascimentoFormatada()));

        // 3.9 - Imprimir o funcionário com a maior idade
        System.out.println("\n--- 3.9 - Funcionário com a maior idade ---");
        Funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null);
        if (maisVelho != null) {
            int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
            System.out.printf("Nome: %s, Idade: %d anos%n", maisVelho.getNome(), idade);
        }

        // 3.10 - Imprimir a lista de funcionários por ordem alfabética
        System.out.println("\n--- 3.10 - Funcionários por ordem alfabética ---");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(f -> System.out.println(f.getNome()));

        // 3.11 - Imprimir o total dos salários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\n--- 3.11 - Total de Salários ---");
        System.out.printf("Total dos salários: R$ %s%n", totalSalarios.setScale(2, RoundingMode.HALF_UP).toString().replace(".", ","));

        // 3.12 - Imprimir quantos salários mínimos cada funcionário ganha
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\n--- 3.12 - Salários Mínimos por Funcionário ---");
        funcionarios.forEach(f -> {
            BigDecimal salariosMinimos = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.printf("Nome: %s ganha %.2f salários mínimos%n", f.getNome(), salariosMinimos);
        });
    }
}