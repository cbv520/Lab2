public class DSAQueue
{
    public static final int DEFAULT_CAPACITY = 100;
    
    private Object[] queue;
    private int count;

    DSAQueue()
    {
        queue = new Object[DEFAULT_CAPACITY];
        count = 0;
    }

    DSAQueue(int maxCapacity)
    {
        queue = new Object[maxCapacity];
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
        return (count == queue.length);
    }

    public void enqueue(Object inObj)
    {
        if(count == queue.length)
        {
            throw new IllegalArgumentException("queue full");
        }
        else
        {
            queue[count] = inObj;    
        }

        count++;
    }

    public Object dequeue()
    {
        Object frontObj = queue[0];

        for(int i = 0; i < queue.length; i++)
        {
            if(queue[i] != null)
            {
                queue[i] = queue[i + 1];
            }
        }

        return frontObj;
    }

    public Object peak()
    {
        return queue[0];
    }

    public void print()
    {
        for(Object o : queue)
        {
            if(o != null)
                System.out.println(o);
        }
    }
}
