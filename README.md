# 📡 Monitoramento de Clima em Tempo Real com WebSocket

Sistema de monitoramento de clima em tempo real utilizando **Spring Boot + WebSocket (STOMP + SockJS)**.

Este projeto demonstra comunicação bidirecional entre cliente e servidor, permitindo:

* 📥 Consultas de clima sob demanda (usuário)
* 🔄 Atualizações automáticas a cada 5 segundos
* 🌎 Integração com API externa de clima (Open-Meteo)

---

## 🚀 Tecnologias utilizadas

* Java 17
* Spring Boot
* WebSocket (STOMP)
* SockJS
* HTML, CSS, JavaScript
* API externa: Open-Meteo

---

## 📁 Estrutura do Projeto

```
📦 websocket-clima
 ┣ 📂 src/main/java/org/example/websocket
 ┃ ┣ 📂 config
 ┃ ┃ ┗ 📄 WebSocketConfig.java
 ┃ ┣ 📂 controller
 ┃ ┃ ┗ 📄 ClimaController.java
 ┃ ┣ 📂 service
 ┃ ┃ ┣ 📄 ClimaService.java
 ┃ ┃ ┗ 📄 TemperaturService.java
 ┃ ┣ 📂 dto
 ┃ ┃ ┗ 📄 ClimaDTO.java
 ┃ ┗ 📂 model
 ┃   ┗ 📄 Cidade.java
 ┣ 📂 src/main/resources
 ┗ 📄 index.html
```

---

## ⚙️ Como rodar o projeto

### 🔧 Pré-requisitos

* Java 17 ou superior
* Maven
* Navegador (Chrome, Edge, etc.)

---

### ▶️ 1. Clonar o repositório

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
```

---

### ▶️ 2. Rodar o back-end

```bash
mvn spring-boot:run
```

O servidor será iniciado em:

```
http://localhost:8080
```

---

### ▶️ 3. Rodar o front-end

Acesse este link no navegador:

```
http://localhost:8080/
```

---

## 🔄 Fluxo de Mensagens

O sistema utiliza WebSocket com dois fluxos distintos:

---

### 🔹 1. Fluxo AUTOMÁTICO (a cada 5 segundos)

Gerado pelo servidor com `@Scheduled`.

#### Etapas:

1. O servidor seleciona uma cidade aleatória
2. Consulta a API Open-Meteo
3. Cria um objeto `ClimaDTO`
4. Envia para:

```
/topic/clima/auto
```

5. Todos os clientes conectados recebem automaticamente

---

### 🔹 2. Fluxo do USUÁRIO (tempo real)

Iniciado pelo front-end.

#### Etapas:

1. O usuário envia dados:

```
/app/sendMessage
```

2. O controller recebe:

```java
@MessageMapping("/sendMessage")
@SendTo("/topic/clima/usuario")
```

3. O serviço busca a temperatura na API
4. Retorna um `ClimaDTO`
5. A resposta é enviada para:

```
/topic/clima/usuario
```

---

## 🔁 Resumo do Fluxo

```
[Usuário]
   ↓
/app/sendMessage
   ↓
[Servidor]
   ↓
/topic/clima/usuario

-------------------------

[Servidor automático]
   ↓ (a cada 5s)
/topic/clima/auto
```

---

## 📡 Endpoints WebSocket

| Tipo        | Endpoint               |
| ----------- | ---------------------- |
| Conexão     | `/api`                 |
| Envio       | `/app/sendMessage`     |
| Recebimento | `/topic/clima/usuario` |
| Recebimento | `/topic/clima/auto`    |

---

## 📦 Exemplo de Comunicação

### 📤 Mensagem enviada pelo usuário

```json
{
  "nome": "Catalão",
  "lat": -18.17,
  "lon": -47.94
}
```

---

### 📥 Resposta do servidor

```json
{
  "cidade": "Catalão",
  "temperatura": 28.5,
  "descricao": "Tempo real",
  "horario": "14:32:10"
}
```

---

## 🎨 Interface do Sistema

O front-end possui duas seções:

### 🟢 Pedidos do Usuário

* Mostra respostas das consultas feitas manualmente

### 🔵 Atualizações Automáticas

* Mostra dados enviados automaticamente a cada 5 segundos

---

## 🌡️ Lógica de Classificação

As mensagens são coloridas conforme a temperatura:

* 🔵 Azul → abaixo de 18°C (frio)
* 🟢 Verde → entre 18°C e 30°C (agradável)
* 🔴 Vermelho → acima de 30°C (quente)

---

## 📸 Print do Sistema

<img width="1905" height="760" alt="Captura de tela 2026-04-08 150359" src="https://github.com/user-attachments/assets/8214f4cc-4b41-4010-9010-f5dd250d1c17" />

<img width="1906" height="799" alt="Captura de tela 2026-04-08 150425" src="https://github.com/user-attachments/assets/f66ab56a-aaa8-4e7e-8e92-d594b530c3be" />


---

## 🧪 Como testar

1. Execute o back-end
2. Acesse o link no navegador `http://localhost:8080/`
3. Observe mensagens automáticas a cada 5 segundos
4. Envie uma cidade manualmente
5. Veja o resultado em tempo real


---

## 👨‍💻 Autor

José Victor Araújo Rojas
