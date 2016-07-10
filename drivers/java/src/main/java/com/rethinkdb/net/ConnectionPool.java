package com.rethinkdb.net;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class ConnectionPool extends GenericObjectPool<Connection> {
	public ConnectionPool(Connection.Builder connectionBuilder, GenericObjectPoolConfig config)
	{
		super(new ConnectionFactory(connectionBuilder), config);
	}
	public ConnectionPool(Connection.Builder connectionBuilder)
	{
		super(new ConnectionFactory(connectionBuilder));
	}
}
