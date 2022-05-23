FROM ubuntu:latest

ARG USERNAME=FamilyAppUser

RUN apt update && apt upgrade -y 
RUN apt install maven -y
RUN apt install openjdk-17-jdk openjdk-17-jre -y
RUN java --version

RUN useradd -m ${USERNAME}

COPY . /home/${USERNAME}/familyApp
RUN chown -R ${USERNAME}:${USERNAME} /home/${USERNAME}/familyApp
WORKDIR /home/${USERNAME}/familyApp
USER ${USERNAME}

EXPOSE 9100
