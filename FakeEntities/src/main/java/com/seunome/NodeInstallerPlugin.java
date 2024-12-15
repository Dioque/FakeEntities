package com.seunome;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.*;

public class NodeInstallerPlugin extends JavaPlugin {



    @Override
    public void onEnable() {
        getLogger().info("Plugin ativado!");

        Thread thread = new Thread(new IniciaPlugin());

        thread.start();

    }

    private class IniciaPlugin implements Runnable{

        @Override
        public void run() {

            String scriptPath = "plugins/ServeBot/bot.js";

            installNodeJS();

            executeNodeScript(scriptPath);
            while (true) ;
        }
    }

    private void executeNodeScript(String scriptPath) {
        try {
            //comando para roda node
            String[] command = {
                    "node", scriptPath
            };

            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true);  //junta as saidas

            Process process = processBuilder.start();

            //le saida
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                getLogger().info(line);  //exibe a saida no console
            }

            //aguarda processo
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                getLogger().info("Script Node.js executado com sucesso!");
            } else {
                getLogger().warning("Houve um erro ao executar o script Node.js.");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            getLogger().warning("Erro ao tentar executar o script Node.js.");
        }
    }


    private void installNodeJS() {
        try {
            //instala npm
            String[] command = {
                    "bash", "-c",
                    "curl -fsSL https://deb.nodesource.com/setup_18.x | sudo -E bash - && sudo apt-get install -y nodejs"
            };

            //configura processo
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            //verifica se deu certo
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                getLogger().info(line);
            }

            // Aguarda o processo terminar
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                getLogger().info("Node.js instalado com sucesso!");
            } else {
                getLogger().warning("Houve um erro na instalação do Node.js.");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            getLogger().warning("Erro ao tentar instalar o Node.js.");
        }
    }
}