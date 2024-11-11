package Client;

import Common.iAccount;

public class User implements iAccount {
    private String IP;
    private int Port;
    private String Name;

    @Override
    public String getIP() {
        return IP;
    }

    @Override
    public int getPort() {
        return Port;
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public void setIP(String IP) {
        this.IP = IP;
    }

    @Override
    public void setPort(int port) {
        this.Port = port;
    }

    @Override
    public void setName(String name) {
        this.Name = name;
    }
}
