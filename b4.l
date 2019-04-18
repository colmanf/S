//b4.l



%{
//Declarations [a-zA-Z0-9]* {return classname;} class {return class;} static {return static1;}"{"|"}" {return brac;}
//"("|")" {return roundbrac;}
//["] {return token1;}
//public|private|protected {return accessspecifier;}
//main|static|class {return main1;}
#include<stdio.h>
#include"y.tab.h"
%}

%%

int|double|string|float|char {return datatype;}
[0-9] {return num;}
[a-zA-Z0-9]+ {return varname;}
"+"|"-"|"*"|"/" {return operator;}
"=" {return eq;}
";" {return sc;}

%%

//b4.y


%{
#include<stdio.h>
void yyerror(char *s);
extern FILE *yyin;
%}

%token datatype varname operator num sc eq 

%%
start : datatype varname eq varnum operator varnum sc {printf("Valid syntax");}| varname eq varnum sc {printf("Valid syntax");}| datatype varname eq varnum sc {printf("Valid syntax");}| datatype varname sc {printf("Valid syntax");};
varnum : varname | num;
%%
void yyerror(char *s)
{
	printf("%s \n", s);
}
int yywrap()
{
	return(1);
}
int main()
{
	extern FILE *yyin;
	yyin = fopen("sample2.java", "r");
	yyparse();
	return 0;
}


