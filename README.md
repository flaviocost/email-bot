# Email Bot

Bot em Java que lê a caixa de entrada do Gmail, procura emails de promoção
pelo assunto e move esses emails para a lixeira.

Projeto de aprendizado em Java, feito com a ajuda de IA como mentor.

## O que ele faz

1. Conecta no Gmail por IMAP.
2. Lê os últimos 50 emails da caixa de entrada.
3. Filtra os emails cujo assunto contém alguma palavra da lista
   (por exemplo, "black friday" e "pré-venda"). A busca não diferencia
   maiúsculas de minúsculas.
4. Move os emails encontrados para a lixeira do Gmail, onde ficam por 30 dias.

## Tecnologias

- Java 21
- Maven
- Jakarta Mail (`com.sun.mail:jakarta.mail`)

## Como configurar

1. Ative a verificação em duas etapas na sua conta Google.
2. Crie uma **senha de app** em Segurança → Senhas de app.
   Contas de trabalho ou escola costumam ter essa opção bloqueada, então
   use uma conta pessoal.
3. Defina duas variáveis de ambiente, sem escrever a senha no código:

```bash
export GMAIL_USUARIO="seu_email@gmail.com"
export GMAIL_SENHA="sua_senha_de_app_sem_espacos"
```

## Como rodar

Na pasta do projeto:

```bash
mvn compile exec:java -Dexec.mainClass="org.example.Main"
```

## Atenção

O bot **move emails para a lixeira**. Antes de usar de verdade, deixe
comentada a linha `moverParaLixeira` no método `executar` da classe
`EmailBot`. Assim ele só mostra quais emails seriam movidos. Confira a lista
e só depois ative a linha.

## Para personalizar

As palavras de busca ficam na `Main`, na lista `palavras`.
Evite palavras curtas como "off", porque a busca procura o texto dentro do
assunto e pode pegar palavras como "coffee".

## Limitações conhecidas

- O filtro olha só o assunto, e não o corpo do email.
- Lê apenas os 50 emails mais recentes (constante `LIMITE` no `EmailReader`).
- O nome da pasta da lixeira é `[Gmail]/Lixeira` (Gmail em português).
  Se o seu Gmail estiver em inglês, troque para `[Gmail]/Trash`.

## Próximos passos

- Versão 2: procurar vagas de emprego e enviar um resumo por email.