package com.brasileirao.model.collections;

import java.util.Arrays;
import java.util.Comparator;

public class CustomList<TElementType>
{
	private Object[] data;
	private int size;
	private static final int DEFAULT_CAPACITY = 10;

	public CustomList(int initialCapacity)
	{
		size = 0;
		data = new Object[initialCapacity];
	}

	public CustomList()
	{
		this(DEFAULT_CAPACITY);
	}

  /**
   * Retorna o elemento localizado no index se este for válido
   * @param index a localização do elemento, deve ser maior ou igual zero e menor que 'size'
   * @return retorna o elemento naquele índice. Pode retornar null caso este tenha sido adicionado previamente
   * @throws IndexOutOfBoundsException se o index dado é menor que zero ou maior ou igual que 'size'
   */
	public final TElementType get(int index) throws ClassCastException, IndexOutOfBoundsException {
		assertIndexIsValid(index);
		return (TElementType) data[index];
	}
	public final void add(TElementType newElement)
	{
		checkInvariants();
		data[size++] = newElement;
	}

	/** Remove um elemento no índice dado e retorna o elemento removido. Esta ação encolhe a lista e invalida índices subsequentes
	* @param index a posição que deve ser removida
	* @return o elemento removido
	* @throws IndexOutOfBoundsException se index for menor que zero ou, maior ou igual a size
	*/
	public final TElementType remove(int index) throws IndexOutOfBoundsException {
		assertIndexIsValid(index);
		TElementType removedElement = (TElementType) data[index];
		size--;
		System.arraycopy(data, index + 1, data, index, size - index - 1);
		return removedElement;
	}

	/** Substitui o elemento no índice dado pelo argumento provido
	* @param index a posição a ser substituída
	* @param newElement o novo elemento que vai ocupar a posição dada
	* @throws IndexOutOfBoundsException se index for menor que zero ou, maior ou igual a size
	*/
	public final void set(int index, TElementType newElement) throws ClassCastException, IndexOutOfBoundsException
	{
		assertIndexIsValid(index);
		data[index] = newElement;
	}

	public final int size()
	{
		return size;
	}

	/** Verifica se o index dado é valido para esta array e acusa uma exceção se este não for o caso
	* @param index o índice a ser testado
	* @throws IndexOutOfBoundsException se index for menor que zero ou, maior ou igual a size
	*/
	private void assertIndexIsValid(int index) throws IndexOutOfBoundsException {
		if (index < 0)
		{
			throw new IndexOutOfBoundsException("Index must be greater than or equal to zero, but is " + index);
		}
		else if (index >= size)
		{
			throw new IndexOutOfBoundsException("Index must not be greater than the list's capacity, but is " + index);
		}
	}

	private void checkInvariants()
	{
		if (size >= data.length)
		{
			resizeArray();
		}
	}

	private void resizeArray()
	{
		int length = data.length;
		Object[] newArray = new Object[2 * length];
		System.arraycopy(data, 0, newArray, 0, length);
		data = newArray;
	}

	@Override
	public final String toString()
	{
		return "List{" + "data=" + Arrays.toString(data) + ", size=" + size + '}';
	}

	public final void bubbleSort(Comparator<TElementType> comparator)
	{
		for (int i = 1; i < size; i++)
		{
			boolean isSorted = true;
			for (int j = 0; j < size - i; j++)
			{
				TElementType currentElement = get(j);
				TElementType nextElement = get(j + 1);
				if (currentElement != null && nextElement != null && comparator.compare(currentElement, nextElement) > 0)
				{
				  set(j, nextElement);
				  set(j + 1, currentElement);
				  isSorted = false;
				}
			}
			if (isSorted) return;
		}
	}
}

