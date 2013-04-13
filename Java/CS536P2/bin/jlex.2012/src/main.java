import java.io.*;

class Main {

  public static void
  main(String args[]) throws java.io.IOException {

    	Yylex lex  = new Yylex(System.in);	

	Token token = lex.yylex();

	while ( token.text != null ) {

		System.out.print("\t"+token.text);
		token = lex.yylex(); // get next token
	}
  }

}
