package com.rethinkdb.net;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class ConnectionFactory extends BasePooledObjectFactory<Connection> {
	private final Connection.Builder builder;

	public ConnectionFactory(Connection.Builder builder)
	{
		this.builder = builder;
	}

	@Override
	public Connection create() throws Exception
	{
		return builder.connect();
	}

	@Override
	public PooledObject<Connection> wrap(Connection obj)
	{
		return new DefaultPooledObject<>(obj);
	}

	@Override
	public boolean validateObject(PooledObject<Connection> p)
	{
		return p.getObject().isOpen();
	}

	@Override
	public void destroyObject(PooledObject<Connection> p) throws Exception
	{
		if (p.getObject().isOpen())
		{
			p.getObject().close();
		}
	}
}
