Para executar a verificação do SonarQube você precisa ter ele rodando em `http://localhost:9000` e ter um projeto criado
com as
informações descritas no `<properties>` do `pom.xml`.

Rode o seguinte comando na raiz do projeto `./mvnw clean verify sonar:sonar "-Dsonar.token=seu-token-aqui"` (trocar
`seu-token-aqui` pelo token do projeto).