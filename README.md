# Descrição do problema 

```sh
Objetivo do projeto Contatos é demostrar como criar uma documentação rest-doc
```
 
## 1. Dependências

Instalar as seguintes ferramentas:

    1. JDK 1.8
    2. Maven
    3. Docker compose
    
O projeto utiliza um banco embarcado (Postgres) para desenvolvimento.
 
## 2. Executando o Projeto
 IDEA Intellij pode ser recomendado
Após baixar o projeto, para executá-lo é necessário rodar os seguintes comandos dentro da pasta raiz.

```sh
$ docker-compose up   
$ mvn clean install   
$ mvn spring-boot:run 
```
## 3. Configuração do pom.xml para rodar o rest-doc
Dependências restdocs-mockmvc

```sh

        <dependency>
            <groupId>org.springframework.restdocs</groupId>
            <artifactId>spring-restdocs-mockmvc</artifactId>
            <scope>test</scope>
        </dependency>

```

build
```sh
<build>
        <finalName>rest-doc-application</finalName>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <filtering>true</filtering>
            </resource>
        </resources>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <executions>
                    <execution>
                        <goals>
                            <goal>build-info</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>

            <plugin>
                <groupId>org.asciidoctor</groupId>
                <artifactId>asciidoctor-maven-plugin</artifactId>
                <version>1.5.2</version>
                <executions>
                    <execution>
                        <id>generate-docs</id>
                        <phase>prepare-package</phase>
                        <goals>
                            <goal>process-asciidoc</goal>
                        </goals>
                        <configuration>
                            <backend>html5</backend>
                            <doctype>book</doctype>
                            <attributes>
                                <snippets>${project.build.directory}/generated-snippets</snippets>
                                <source-highlighter>highlight.js</source-highlighter>
                            </attributes>
                        </configuration>
                    </execution>
                </executions>
            </plugin>

            <plugin>
                <groupId>com.bazaarvoice.maven.plugins</groupId>
                <artifactId>s3-upload-maven-plugin</artifactId>
                <version>1.4</version>
                <configuration>
                    <bucketName>contatodocumentation</bucketName>
                    <source>${project.build.directory}/generated-docs/index.html</source>
                    <destination>rest/doc/${branch}/index.html</destination>
                    <endpoint>s3-sa-east-1.amazonaws.com</endpoint>
                    <recursive>true</recursive>
                </configuration>
                <dependencies>
                    <dependency>
                        <groupId>org.springframework.restdocs</groupId>
                        <artifactId>spring-restdocs-asciidoctor</artifactId>
                        <version>1.2.1.RELEASE</version>
                    </dependency>
                </dependencies>
            </plugin>
        </plugins>
    </build>
```

## 4. Test
ContatoController extend AbstractTest e executas os métodos abaixo:

```sh

         findAll - create - update e delete

```

## 5.Configure o diretorio asciidoc

```sh
contado.adoc
index.adoc

```

## 6.Documentação Rest-Doc

```sh
target/generated-docs/index.html

```

## 7.Snippets

```sh
target/generated-docs/generated-snippets

create-contato
delete-contato
find-all-contato
update-contato

```

## 8.Encerrar o docker
```sh
$ docker-compose down

```
