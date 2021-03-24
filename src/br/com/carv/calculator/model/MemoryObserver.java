package br.com.carv.calculator.model;

@FunctionalInterface
public interface MemoryObserver 
{
	public void changedValue(String newvValue);
}
