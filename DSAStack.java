public class DSAStack
{
    public static final int DEFAULT_CAPACITY = 100;

    private Object[] stack;
    private int count;

    DSAStack()
    {
        stack = new Object[DEFAULT_CAPACITY];
        count = 0;
    }

    DSAStack(int maxCapacity)
    {
        stack = new Object[maxCapacity];
        count = 0;
    }

    public int getCount()
    {
        return count;
    }

    public boolean isEmpty()
    {
        return (count == 0);
    }

    public boolean isFull()
    {
        return (count == stack.length);
    }

    public void push(Object inObject)
    {
        if(isFull())
        {
            throw new ArrayIndexOutOfBoundsException("Stack is full");
        }
        else
        { 
            stack[count] = inObject;
            count++;
        }  
    }

    public Object pop()
    {
        Object topObj = top();
        count--;
        return topObj;
    }

    public Object top()
    {
        Object topObj = null;
    
        if(isEmpty())
        { 
            throw new ArrayIndexOutOfBoundsException("Stack is empty");
        }
        else
        {
            topObj = stack[count - 1];
        }

        return topObj;
    }
}




