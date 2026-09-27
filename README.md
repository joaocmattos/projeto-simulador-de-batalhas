Para executar a verificação do SonarQube você precisa ter ele rodando em `http://localhost:9000` e ter um projeto criado
com as
informações descritas no `<properties>` do `pom.xml`.

```xml
<sonar.projectKey>simulador-de-batalhas</sonar.projectKey>
<sonar.projectName>simulador de batalhas</sonar.projectName>
<sonar.host.url>http://localhost:9000</sonar.host.url>
```

Rode o seguinte comando na raiz do projeto `./mvnw clean verify sonar:sonar "-Dsonar.token=seu-token-aqui"` (trocar
`seu-token-aqui` pelo token do projeto).
