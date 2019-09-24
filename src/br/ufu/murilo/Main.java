package br.ufu.murilo;

import br.ufu.murilo.util.Classification;

public class Main {

    public static void main(String[] args) throws Exception {

        Classification c = new Classification("src/br/ufu/murilo/resources/data.txt", 3, 2, true);

        c.avaliarQualidadeDeClassificador();


    }
}
