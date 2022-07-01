package brasileirao.model.collections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class CustomList<TElementType>
{
	public static final int INVALID_INDEX = -1;

	/** Array de dados internos da Lista. É do tipo Object devido às limitações de Generics em Java*/
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
	public final TElementType get(int index) throws ClassCastException, IndexOutOfBoundsException
	{
		assertIndexIsValid(index);
		return (TElementType) data[index];
	}

  /** Adiciona um elemento ao final da lista
   * @param newElement o elemento a ser adicionado
   */
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
	public final TElementType remove(int index) throws IndexOutOfBoundsException
	{
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

	/**
	* @return o número de elementos na lista
	*/
	public final int size() {
								  return size;
											  }

	/** Verifica se o index dado é valido para esta array e acusa uma exceção se este não for o caso
	* @param index o índice a ser testado
	* @throws IndexOutOfBoundsException se index for menor que zero ou, maior ou igual a size
	*/
	private void assertIndexIsValid(int index) throws IndexOutOfBoundsException
	{
		if (index < 0)
		{
			throw new IndexOutOfBoundsException("Index must be greater than or equal to zero, but is " + index);
		}
		else if (index >= size)
		{
			throw new IndexOutOfBoundsException("Index must not be greater than the list's capacity, but is " + index);
		}
	}

	/** Garante a consistência da lista*/
	private void checkInvariants()
	{
		if (size >= data.length)
		{
			resizeArray();
		}
	}

	/** Redimensiona a array data*/
	private void resizeArray()
	{
		Object[] newArray = new Object[2 * data.length];
		System.arraycopy(data, 0, newArray, 0, data.length);
		data = newArray;
	}

	@Override
	public final String toString()
									  {
										 return "List{" + "data=" + Arrays.toString(data) + ", size=" + size + '}';
																												   }

	/** Ordena a lista de acordo com o comparador dado. Complexidade do sort é O(n^2), podendo ser melhorada utilizando o merge sort
	* @param comparator o comparador a ser utilizado para ordenar a lista
	*/
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

	/** Realiza uma busca linear na lista, procurando o elemento dado. A complexidade é O(n), podendo ser melhorada utilizando o binary search (se a lista estiver ordenada utilizando o merge sort)
	* @param predicate o predicado a ser utilizado para buscar o elemento
	* @return o índice do elemento encontrado ou INVALID_INDEX se não encontrado
	*/
	public final int linearSearch(Predicate<TElementType> predicate)
	{
		for (int i = 0; i < size(); i++)
		{
			if (predicate.test(get(i)))
			{
				return i;
			}
		}
		return INVALID_INDEX;
	}
}

