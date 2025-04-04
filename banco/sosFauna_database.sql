drop database sosfauna;
create database sosFauna;
use sosFauna;

create table usuarios(
id int auto_increment primary key,
cpf varchar(11) not null,
nome varchar(50) not null,
dt_nascimento date null,
email varchar(255) not null,
telefone varchar(11),
senha varchar(255) not null,
data_criacao datetime DEFAULT CURRENT_TIMESTAMP,
acesso boolean not null
);

create table orgaos(
id int auto_increment primary key,
nome varchar(100) not null,
cnpj varchar(50) not null,
descricao varchar(100),
email varchar(50) not null,
senha varchar(50) not null,
telefone varchar(11) not null,
rede_social varchar(255),
endereco varchar(255) not null,
foto_perfil blob,
acesso boolean not null,
data_criacao datetime DEFAULT CURRENT_TIMESTAMP
);

create table denuncias(
id int auto_increment primary key,
animal varchar(255) not null,
denunciado varchar(50),
descricao varchar(255) not null,
data_ocorrido date not null,
hora_ocorrido time not null,
bairro varchar(100) not null,
numero varchar(10) not null,
rua varchar(50) not null,
cep varchar(50),
data_criacao datetime DEFAULT CURRENT_TIMESTAMP,
id_usuario int,
status_denuncia enum('Em Aberto', 'Em Analise','Em Diligencia', 'Concluida', 'Cancelada'),
id_orgao int,
FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
FOREIGN KEY (id_orgao) REFERENCES orgaos(id)
);

create table animais_adocao(
id int auto_increment primary key,
nome varchar(40),
especie varchar(40) not null,
idade int not null,
sexo enum('M', 'F') not null,
foto blob,
data_criacao datetime DEFAULT CURRENT_TIMESTAMP,
status_adocao enum('disponivel', 'adotado'),
id_orgao int,
FOREIGN KEY (id_orgao) REFERENCES orgaos(id)
);


