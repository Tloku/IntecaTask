FROM ubuntu:latest

ARG USERNAME=FamilyMemberAppUser

RUN apt update && apt upgrade -y 
RUN apt install maven -y
RUN apt install openjdk-17-jdk openjdk-17-jre -y
RUN java --version

RUN useradd -m ${USERNAME}

COPY . /home/${USERNAME}/familyMemberApp
RUN chown -R ${USERNAME}:${USERNAME} /home/${USERNAME}/familyMemberApp
WORKDIR /home/${USERNAME}/familyMemberApp
USER ${USERNAME}

EXPOSE 9110
