# `Desafio GreenMile`

Repositório destinado a resolução do desafio proposto pela GreenMile.



### Permição de Usuários da API

- `*/ognz/**` - Organizador
- `*/ptcp/**` - Participante
- `participants/sign-up` - Sem restrição
- `/organizers/sign-up` - Sem restrição

### Endpoints da API

**POST**

- `/hackathons/ognz/` - Adiciona um Hackathon a um Organizador.

**GET**

- `/hackathons/ognz/` Retorna todas as Hackathons pertencentes ao Organizador Logado.

**GET**

- `/hackathons/ptcp/` Retorna todas as hackathons que ainda estão com as inscrições abertas.

**GET**

- `/hackathons/ognz/{hackathon_id}` Retorna o hackathon caso pertença ao organizador logado.

**PUT**

- `/hackathons/ognz/` - Atualiza uma hackathon que pertença ao organizador logado.

**DELETE**

- `/hackathons/ognz/{hackathon_id}` Remove o hackathon caso pertença ao organizador logado.

**POST**

- `/participants/sign-up` - Adiciona um usuário do tipo participante no sistema.

**POST**

- `/organizers/sign-up` - Adiciona um usuário do tipo organizador no sistema.

**GET**

- `/users/ptcp` - Lista todos os usuário do sistema.

**GET**

- `/teams/ognz/hackathon/{hackathon_id}` - Retorna todos os de times de um hackathon que pertença ao organizador logado.

**POST**

- `/teams/ptcp/subscribe-team/hackathon/{hackathon_id}` - Inscreve um time em uma hackathon

**DELETE**

- `/teams/ptcp/unsubscribe-team/{team_id}` - Cancela a inscrição do time desde que o participante logado seja um dos componentes do time.
