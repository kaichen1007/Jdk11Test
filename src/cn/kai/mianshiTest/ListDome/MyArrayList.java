package cn.kai.mianshiTest.ListDome;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class MyArrayList implements Serializable {

    //默认第一次扩容为10
    private static final int DEFAULT_CAPACITY = 10;

    //用于初始化
    private static final Object[] EMPTY_ELEMENT_DATA = {};

    //实际存储的对象  transient防止序列化
    transient Object[] elementData;

    //实际对象存储大小 默认为0
    private int size;

    //无参构造 进行初始化对象
    public MyArrayList(){
        this.elementData = EMPTY_ELEMENT_DATA;
    }

    //有参构造 传入自定义空间空间
    public MyArrayList(int initialCapacity){
        //检查传入参数是否正常
        if (initialCapacity < 0){
            //自定义容量数组
            this.elementData = new Object[initialCapacity];
        }else if (initialCapacity == 0){
            this.elementData = EMPTY_ELEMENT_DATA;
        }else {
            throw new IllegalArgumentException("参数异常！");
        }
    }

    public boolean add(Object e){
        //判断容量，传入最小需要容量
        ensureCapacityInternal(size + 1);
        //插入对象，使用下标进行赋值
        elementData[size++] = e;

        return true;
    }

    //用于判断容量，传入最小需要容量
    public void ensureCapacityInternal(int minCapacity){
        //判断是否是初次扩容
        if (elementData == EMPTY_ELEMENT_DATA){
            //初始默认容量和最小需要容量进行比较，谁大谁赋值
            minCapacity = Math.max(minCapacity,DEFAULT_CAPACITY);
        }
        //判断是否需要扩容，如果最小需要容量大于数组长度则需要扩容
        if (minCapacity - elementData.length > 0){
            //老容量
            int oldCapacity = elementData.length;
            //新容量 = 老容量 + 老容量/2
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            //如果最小需要容量 > 新容量，则把最小需要容量赋值给新容量 用于初始化容量
            if (minCapacity - newCapacity > 0){
                newCapacity = minCapacity;
            }
            //创建新的数组，进行重新指向堆空间
            Object[] objects = new Object[newCapacity];
            //进行copy
            System.arraycopy(elementData,0,objects,0,elementData.length);
            //重新指定新的堆空间
            elementData = objects;
        }
    }

    //通过下标获取对象
    public Object get(int index){
        rangeCheck(index);
        return elementData[index];
    }
    //判断下标是否合法
    private void rangeCheck(int index) {
        if (index > size || index <0){
            throw new IndexOutOfBoundsException("越界");
        }
    }

    //根据对象获取下标
    public int indexOf(Object o){
        if (o == null){
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])){
                    return i;
                }
            }
        }else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])){
                    return i;
                }
            }
        }
        return -1;
    }

    //根据下标更新对象
    public Object set(int index,Object o){
        rangeCheck(index);
        Object oldValue  = elementData[index];
        elementData[index] = o;
        return oldValue;
    }

    //根据下标进行删除对象
    public Object remove(int index){
        rangeCheck(index);
        Object oldValue = elementData[index];
        //获取要删除后面元素的个数
        int numMoved = size - index - 1;
        //大于0才可以删除
        if (numMoved > 0){
            //从elementData的index下一个开始复制，复制到elementData的index处复制后面元素的个数
            System.arraycopy(elementData,index + 1,elementData,index,numMoved);
        }

        //滞空数组最后一个
        elementData[--size] = null;

        return oldValue;
    }

    public int getSize(){
        return this.size;
    }


}
