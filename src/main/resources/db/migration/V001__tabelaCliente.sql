CREATE TABLE API_ENTREGA.CLIENTE (
	id bigint not null auto_increment,
    nome varchar(60) not null,
    email varchar(255) not null,
    telefone varchar(20) not null,
    
    constraint pk_cliente primary key (id)
);