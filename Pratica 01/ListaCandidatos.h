#include "NoCandidato.h"
#include <fstream>
#include <cstring>
#include <iostream>

using namespace std;


class ListaCandidatos {
	
public:
    NoCandidato *head;
    int size;

    bool estaVazia(){
        return head==NULL;
    }

    ListaCandidatos(){
        this->head=NULL;
		this->size=0;
    }
    ListaCandidatos(string nomeDoArquivo){
		ifstream fcin(nomeDoArquivo.c_str());
		string dados;
		getline(fcin,dados);
		cout << "criacao da lista de candidatos de:" + dados  << endl;
		while(getline(fcin,dados)){
    
		Candidato c(dados);
		adicioneComoHead(new Candidato (dados));

		}
	}
    int tamanho(){
    	return this->size;
    }
    void adicioneComoHead(Candidato *c){
	 NoCandidato* m = NULL;
	 m = new NoCandidato(c,NULL);
         m->next=head;
         this->head=m;
         this->size=size+1;
    }
    
    string toString(){
		return this->head->toString();			
	}
    
    bool remover(string nome, string sobrenome){
		//não testado.
		if(this->head->conteudo->igual(nome,sobrenome)){
			this->head=NULL;
			delete this->head;
			this->head=this->head->next;
		}
	}

};
