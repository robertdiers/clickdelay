FROM codercom/code-server:latest

USER root

#install environment requirements
RUN apt update
RUN apt install -y maven
RUN apt install -y openjdk-11-jdk
RUN apt install -y wget
RUN apt install -y docker.io

#install code extensions
RUN code-server --install-extension fwcd.kotlin
RUN code-server --install-extension mathiasfrohlich.Kotlin
RUN code-server --install-extension vscjava.vscode-java-pack
RUN code-server --install-extension GabrielBB.vscode-lombok

#git
RUN git config --global user.email "robertdiers@gmail.com"
RUN git config --global user.name "Robert Diers"
