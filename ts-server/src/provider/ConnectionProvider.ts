import "reflect-metadata";
import { Connection } from "typeorm";
import { createConnection } from 'typeorm';

export default class ConnectionProvider {
    private static myConnection: Connection;

    public static async setConnection() {
        if (!ConnectionProvider.myConnection) {
            ConnectionProvider.myConnection = <Connection>await createConnection();
        }
    }

    public static connection() {
        return ConnectionProvider.myConnection;
    }
}
