Desafio Técnico



Objetivo
No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação.
A partir disso, você precisa criar uma solução back-end para gerenciar essas sessões de votação.
Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de uma API



REST:
- Cadastrar uma nova pauta;
- Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default);
- Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é
identificado por um id único e pode votar apenas uma vez por pauta);
- Contabilizar os votos e dar o resultado da votação na pauta.



É importante que as pautas e os votos sejam persistidos.


---pauta
    ---config
    ---controller
    ---converter
    ---dto
    ---entity
    ---enumeration
    ---exception
    ---repository
    ---servic


    Lambda; Optional; Streams; Test Unit Clean Code;
    Exception
    Banco Mongo, Postgre, H2
    Scheduler
