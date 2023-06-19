package Annotation;

// (quando usado o @Target(ElementType.TYPE))
@IPrimeiraAnotacao(value = "Victor", bairros = "Bela Vista", numeroCasa = 114)
public class ClasseComAnotacao {

    //(qunado usado o @Target(ElementType.FIELD))
    @IPrimeiraAnotacao(value = "Bruno", bairros = {"Cerqueira César, Higienópolis"}, numeroCasa = 21, valores = 55d)
    private String nome;

    @IPrimeiraAnotacao(value = "Santos", bairros = "Republica", numeroCasa = 5, valores = 30d)
    public ClasseComAnotacao() {

    }
}
