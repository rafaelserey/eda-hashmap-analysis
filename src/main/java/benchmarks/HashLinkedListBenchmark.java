package benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.List;
import java.util.concurrent.TimeUnit;

import data_structures.hashstructures.HashLinkedList;
import data_structures.io.reader.CsvReader;
								
@State(Scope.Thread)
@Fork(value = 5)
@Warmup(iterations = 5, time = 5)
@Measurement(iterations = 10, time = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)


public class HashLinkedListBenchmark {

	@Param({"0.5","0.75","1.5"})
	private float loadFactor;

	private List<Integer> inputValues;
	private HashLinkedList<Integer, Integer> hashLinkedList;
	private HashLinkedList<Integer, Integer> filledLinkedList;

   @Setup 
    public void setUp() {
    	inputValues = CsvReader.read("data/unsorted_data_alt.csv");
		hashLinkedList = new HashLinkedList<Integer, Integer>(loadFactor);
		filledLinkedList = new HashLinkedList<Integer, Integer>(loadFactor);

		for(int value: inputValues) 
			filledLinkedList.put(value, value);
	}

	@Benchmark
    public void putAll(Blackhole blackhole) {
        for (Integer value : inputValues) {
            hashLinkedList.put(value, value);
        }
    }

	@Benchmark
    public void getAll(Blackhole blackhole) {
        for (Integer value : inputValues) {
            blackhole.consume(filledLinkedList.get(value));
        }
    }
}
