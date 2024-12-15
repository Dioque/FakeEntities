const mineflayer = require('mineflayer')

function createBot(username) {
  const bot = mineflayer.createBot({
    host: 'localhost', 
    port: 25565,       
    username: username, 
  })

  bot.on('spawn', () => {
    console.log(`${username} entrou no servidor!`)
    bot.chat('/tell Dioque testes')
  })

  bot.on('error', err => console.log(err))
  bot.on('end', () => console.log(`${username} saiu do servidor!`))
}


createBot('Dioque2')
createBot('lucas')
createBot('Mateus')
createBot('Joaos')
createBot('lux')
