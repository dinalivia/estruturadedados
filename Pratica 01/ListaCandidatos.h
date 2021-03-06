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
		this->head=NULL;
		this->size=0;
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
    
    bool remove(string nome, string sobrenome){
		//não testado.
		if(this->estaVazia()==true)
			return false;
		else if(this->head->conteudo->igual(nome,sobrenome)){
			NoCandidato* m = NULL;
	 		m = this->head->next;
			this->head=NULL;
			delete this->head;
			this->head=m;
			this->size=size-1;
			return true;
		}
		else{
			NoCandidato* atual = NULL;
	 		atual = this->head;
	 		while(atual->next!=NULL){
		 		if (atual->next->conteudo->igual(nome,sobrenome)){
	 				NoCandidato* m = NULL;
			 		m = atual->next->next;
					atual->next=NULL;
					delete atual->next;
					atual->next=m;
					this->size=size-1;
					atual=atual->next;
					return true;
				}
				atual=atual->next;
			}
		    return false;	
		}
		
	}
	void filtrarCandidatos(int nota){
		NoCandidato* atual = NULL;
		atual = this->head;
 		while(atual!=NULL){
	 		if (atual->conteudo->nota<nota)
				remove(atual->conteudo->nome,atual->conteudo->sobrenome);
			atual=atual->next;
		}
	}
	void concatena(ListaCandidatos* l){
		NoCandidato* atual = NULL;
		atual = this->head;
		while(atual->next!=NULL){
			atual=atual->next;
		}
		NoCandidato* atual_l = NULL;
		atual_l = l->head;
		atual->next=atual_l;
		this->size=size+1;
		atual_l=atual_l->next;
		while(atual_l!=NULL){
			atual=atual_l;
			atual_l=atual_l->next;
			this->size=size+1;			
		}
	}
	

};
