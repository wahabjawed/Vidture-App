package com.silversages.viditure.Network;

public interface INetwork {
	public abstract void postRequestExecute();

	public abstract void preRequestExecute();

	public abstract void progressUpdate(String update);
}
