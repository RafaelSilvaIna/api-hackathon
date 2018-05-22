# `Desafio GreenMile`

Repositório destinado a resolução do desafio proposto pela GreenMile.



### Permição de Usuários da API

- `*/organizer/**` - Organizador
- `*/participant/**` - Participante
- `participants/sign-up` - Sem restrição
- `/organizers/sign-up` - Sem restrição

### Endpoints da API

**POST**

- `/hackathons/organizer/` - Adiciona um Hackathon a um Organizador.

**GET**

- `/hackathons/organizer/` Retorna todas as Hackathons pertencentes ao Organizador Logado.

**GET**

- `/hackathons/participant/` Retorna todas as hackathons que ainda estão com as inscrições abertas.

**GET**

- `/hackathons/organizer/{hackathon_id}` Retorna o hackathon caso pertença ao organizador logado.

**PUT**

- `/hackathons/organizer/` - Atualiza uma hackathon que pertença ao organizador logado.

**DELETE**

- `/hackathons/organizer/{hackathon_id}` Remove o hackathon caso pertença ao organizador logado.

**POST**

- `/participants/sign-up` - Adiciona um usuário do tipo participante no sistema.

**POST**

- `/organizers/sign-up` - Adiciona um usuário do tipo organizador no sistema.

**GET**

- `/users/participant` - Lista todos os usuário do sistema.

**GET**

- `/teams/organizer/hackathon/{hackathon_id}` - Retorna todos os de times de um hackathon que pertença ao organizador logado.

**POST**

- `/teams/participant/subscribe-team/hackathon/{hackathon_id}` - Inscreve um time em uma hackathon

**DELETE**

- `/teams/participant/unsubscribe-team/{team_id}` - Cancela a inscrição do time desde que o participante logado seja um dos componentes do time.
