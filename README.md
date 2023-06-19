# Annotation e Exception

## Annotation / Anotação
As anotações Java são um mecanismo para adicionar informações de metadados ao nosso código-fonte. Elas foram introduzidas na versão 5 do Java.

Embora possamos anexá-las a classes, interfaces, métodos e campos, as anotações por si mesmas não têm efeito na execução de um programa. É preciso ter outra inteligência para a leitura das mesmas, com **Reflection**.

Para definir uma anotação no código Java, usamos o símbolo arroba `@` seguido do nome da mesma. Dependendo da categora da annotation, pode ser necessário incluir dados a ela, no formato de pares **nome=valor**.

*Exemplo:*
```
@Override
public String toString() {
    return "Cliente{" +
            "nome='" + nome + '\'' +
            ", cpf=" + cpf + 
            '}';
}
```

### Tipos de anotações

- Anotações marcadores
- Anotações de valor único
- Anotações completas

#### Anotações marcadoras

São aquelas que não possuem membros. São identificadas apenas pelo nome, sem dados adicionais. Por exemplo, o exemplo usado em Anotações

#### Anotações de valor único

São similares às anteriores, no entanto, possuem um único membro, chamdo valor/value. Elas são representadas pelo nome da anotação e um par **nome=valor**, ou simplesmente com o valor, entre parênteses.

```
@Target (ElementType.ANNOTATION_TYPE)
public @inteface Persistente {
```

```
@Target (value = ElementType.ANNOTATION_TYPE)
public @inteface Persistente {
```

#### Anotações completas
São aquelas que possuem múltiplos membros. Portanto, neste tipo, devemos usar a sintaxe completa para cada par **nome=valor**. Neste caso, cada par é informado separado do outro por uma vírgula. Por exemplo:

``@Version(major=1, minor=0, micro=0)``

```
@Deprecated(forRemoval = true, since = "1.2")
public interface Percistente {
    public Long getCodigo();
}
```

## Meta-anotações

São anotações utilizadas na criação de anotações ou na marcação.
Estão no pacote **java.lang.annotation**

- @Retention
- @Documented
- @Target
- @Inherited

### @Retention

As anotações podem estar presentes apenas no código-fonte ou no binario de classes, ou interfaces.

Ela suporta três valores:
- **SOURCE**, para indicar que as anotações marcadas não estarão no código binário.
- **CLASS**, para gravar as anotações no arquivo ``.class``, mas não estarão disponíveis em tempo de execução.
- **RUNTIME**, para indicar que as anotações estarão dispoíveis em tempo de eecução.

*Exemplo:*
```
@Retention(RetentionPolicy.RUNTIME)
public @interface ExemploAnotação {
}
```

### @Documented

É uma anotação marcadora usada para indicar que os tipos anotação anotados com ela serão incluídos na documentação *Javadoc*.

*Exemplo:*
```
@Documented 
@Retention(RetentionPolicy.RUNTIME)
public @interface ExempoAnotacao {
}
```

### @Target

Ao criar um tipo de anotação é possível estabelecer qual elemnetos de uma classe podem ser anotados com ele.
(construtor, variável local, parâmetro de método, método e etc...)

Para obter efeito, usamos `@Target`, a qual suporta os valores (cadaum destinado a definir o elemento que se pretende anotar):

- **TYPE** - Classe ou interfaces
- **FIELD** - Propriedades de classes
- **METHOD** - Métodos
- **PARAMETER** - Parâmetros
- **CONSTRUCTOR** - Construtor
- **LOCAL_VARIABLE** - Variáveis locais

*Exemplo:*
```
@Documented
@Retention(RententionPolicy.Runtime)
@Target(ElementType.TYPE)
pubic @interface ExemploAnotacao {
}
```

### @Inherited

Por padrão anotações declaradas numa classe não são herdadas pelas subclasses. Mas, se for necessário que essa heraça ocorra, então o tipo de anotação que desejamos que seja herdado deve ser anotado com `@Inherited`

É importante destacar que a utilização desta meta-anotação restringe-se apenas a classes. Por exemplo, anotções em interfaces não são herdadas pelas classes que as implementam.

*Exemplo:*
```
@Documented
@Retention(RententionPolicy.Runtime)
@Target(ElementType.TYPE)
@Inherited
pubic @interface ExemploAnotacao {
}
```

## Regras para criar anotações

- Por convenção, o nome do único membro num tipo de anotação com um único elemnto deve ser `value`.
- A declaração de um método num tipo de anotação não pode ter parâmetro e nem uma cláusula throws, que indica um lançamento de exceção.
- O método não deve possuir corpo - ele é especificado como um método abstrato;
- O tipo de retorno do método será o tipo de dado do elemento;
- O tipo de retorno deve ser um dos seguintes: primitivos, String, Class, enum ou um array cujo tipo seja um dos precedentes.

*Exemplo:*
```
@Documented
@Retention(RententionPolicy.Runtime)
@Target(ElementType.TYPE)
@Inherited
pubic @interface ExemploAnotacao {
    long value();
    String[] nomes();
    RetentionPolicy[] value2();
    RetentionPolicy value3();
    String nomeDefalt() default "Victor";
}
```

*Exemplo de uso:*

```
@ExemploAnotacao(value = 1, nomes = {"Victor"},
    values2 = {RetentionPolicy.RUNTIME},
    values3 = RetentionPolicy.RUNTIME)
public class UsandoAnotacao {
}    
```

## Exceptions / Exceções

Exceptions ou Exceção (Erro) é um evento não esperado que ocorre no sistema quando está em tempo de execução (Runtime).
Geralmente quando o sistema captura alguma exceção o fluxo do código fica interrompido.

Para conseguir capturar uma exceção, é preciso fazer antes o tratamento. O uso dos tratamentos é importante nos sistemas porque auxilia em falhas como: comunicação leitura e escrita de arquivos, entrada de dados inválidos, acesso a elementos fora de índice, entre outros.

*Exemplos:*

``` 
try {
    //Codigo aqui
} catch (Exception e) {
    // Captura e tratamento do erro aqui
}        
```

``` 
try {
    //Codigo aqui
} catch (Exception e) {
    // Captura e tratamento do erro aqui
} finally {
    // Codigo sempre é executado
}    
```

``` 
try {
    //Codigo aqui
} catch (NullPointerException e) {
    // Captura e tratamento do erro aqui
} catch (Exception e) {
    // Captura e tratamento do erro aqui
}  finally {
    // Codigo sempre é executado
}        
```

``` 
try {
    //Codigo aqui
} catch (NullPointerException | indexOutOfBoundsException e) {
    // Captura e tratamento do erro aqui
} catch (Exception e) {
    // Captura e tratamento do erro aqui
}  finally {
    // Codigo sempre é executado
}        
```

``` 
private static void comTratamentoException() {
    String frase = null;
    String nova frase = null;
try {
    novaFrase = frase.toUpperCase();
} catch (NullPointerException e) {
    // Tratamento da Exceção
    System.out.println("A frase inicial está nula);
    frase = "Frase vazia";
    novaFrase = frase.toUpperCase();
}
System.out.println("Frase antiga: " + frase);
System.out.println("Frase nova: " + novafrase);
}
```

### Categorias

- Checked Exceptions
- Unchecked Exceptions

Quando as ``checked Exceptions`` devem ser usadas?
- Use *checked Exception* quando houver um erro recuperável ou um requisito de negócio importante.

Quando as ``unchecked Exceptions`` devem ser usadas?
- Use *unchecked Excepyin quando não houver recuperação. Por exemplo, quando a memória do servidor é usada em excesso.

``RuntimeException`` é usado para os erros quando o seu aplicativo não pode recuperar. Por exemplo, *NullPointerException e*

``ArrayOutBoundsException``. **Nunca** tente tratar exceptions dessa categoria.

### Cláusulas thow/thows

As cláusulas ``throw`` e `throws` podem ser entendidas como ações que propagam exceções, ou seja, em alguns momentos existem exceções que não podem ser tratadas no mesmo método que gerou a exceção. Nesses casos, é necessário propagar a exceção para um nível acima na pilha

```
public static void (doble valor) throws LimiteSaqueException {
    if (valor > 400) {
        LimiteSaqueException erro = 
        new LimiteSaqueException("Valor solicitato é maior que o seu limite diário.");
    throw erro;
    } else {
        System.out.println("valor retirado da conta: R$ " + valor);
    }
}  
    private static void exception() {
        try {
            ExemploThrow.saque( 500);
        } catch (LimiteSaqueException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }                      
```

```
public static void saqueRunException (doble valor) {
    if (valor > 400)
        IllegalArgumentException erro = 
            new IllegalArgumentException("Valor solicitado é maior que o seu limite diário.");
        throw erro;
    } else {
        System.out.println("Valor retirado da conta: R$" + valor);
    }
}
    private static void runtimeException() {
        ExemploThrow.saqueRuntimeException(500);
    }                   
```