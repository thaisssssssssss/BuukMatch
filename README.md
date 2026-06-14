# BuukMatch
Sistema de trocas de livros por meio de gostos em comum (match) para a disciplina de Projeto Integrado I

### Fiz alguns dos laboratórios que o professor passou e criei uma espécie de chat, bem incompleto ainda, mas só pra entender o funcionamento.

### O HTML a IA que fez 

### Pra rodar:
```bash
./mvnw spring-boot:run
```


-----------------------------
#### (readme oficial)

# BuukMatch

Este repositório contém o código fonte do BuukMatch!

O `(a?)` BuukMatch é uma plataforma que tem como propósito conectar leitores por meio de um ambiente de resenhas e troca de itens literários. 

O sistema busca unir amantes de livros que  residem próximos uns aos outros, por meio de um ambiente de troca que funciona por meio de publicações dos livros que se deseja desapegar, levando em conta a distância entre os interessados e também os gostos pessoais. 

Diferente de uma simples doação, nosso produto visa o engajamento de ambas as partes, de modo que a troca seja mútua e satisfatória aos dois lados. Além disso, é uma plataforma que permite troca de mensagens, postagem de resenhas e comentários, o que fomenta a parte interativa do sistema.


## 🛠️ Ferramentas
A seguir, estão listadas o conjunto de ferramentas escolhidas para o desenvolvimento do projeto e seus respectivos papéis.

### GitHub e Git
O controle de versões do projeto é realizado utilizando o `git`. E, como hospedagem para o repositório remoto, foi usado o `github`. A escolha foi devido à fama e confiabilidade oferecida pelas duas ferramentas.

#### Issue Tracking (GitHub)
O GitHub também será utilizado para fazer o issue tracking do sistema. Isto é devido à interface amigável e intuitiva da plataforma na criação e atualização do estado das issues.

#### CI/CD (GitHub)
Por fim, a funcionalidade `GitHub Actions` também será utilizada para configurar o workflow de CI/CD do projeto. Esta escolha é devido, mais uma vez, da interface amigável e intuitiva do github na criação das funcionalidade de integração e deploy contínuos.

### Maven
A ferramenta de build escolhida foi o `Maven`. Esta escolha foi devido à maturidade da ferramenta com a principal linguagem de programação utilizada (Java).

### JUnit
A ferramenta de testes escolhida foi o `JUnit`. Esta escolha foi (novamente) motivada pela principal linguagem de programação utilizada ser Java. Além do fato de que o `Maven` oferece uma integração fácil com a biblioteca escolhida.

### Docker
A ferramenta utilizada para criar o container do projeto foi o `Docker`. Esta escolha foi motivada pelo fato da maioria das ferramentas de deploy disponíveis do mercado terem suporte a este tipo de container. Além do fato de ser a ferramenta mais famosa no segmento que atua.

## 🧱 Frameworks
A seguir, está listado o conjunto de frameworks que foram reutilizados para facilitar o desenvolvimento do projeto.


