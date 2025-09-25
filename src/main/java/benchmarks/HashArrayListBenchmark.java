package benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.List;
import java.util.concurrent.TimeUnit;

import data_structures.hashstructures.HashArrayList;
import data_structures.io.reader.CsvReader;
								
@State(Scope.Thread)
@Fork(value = 5)
@Warmup(iterations = 5, time = 5)
@Measurement(iterations = 10, time = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)


public class HashArrayListBenchmark {

	@Param({"0.5","0.75","1.5"})
	private float loadFactor;

	private List<Integer> inputValues;
	private HashArrayList<Integer, Integer> hashArrayList;
	private HashArrayList<Integer, Integer> filledHashArrayList;

   @Setup 
    public void setUp() {
    	inputValues = CsvReader.read("data/unsorted_data_alt.csv");
		hashArrayList = new HashArrayList<Integer, Integer>(loadFactor);
		filledHashArrayList = new HashArrayList<Integer, Integer>(loadFactor);

		for(int value: inputValues) 
			filledHashArrayList.put(value, value);
	}

	@Benchmark
    public void putAll(Blackhole blackhole) {
        for (Integer value : inputValues) {
            hashArrayList.put(value, value);
        }
    }

	@Benchmark
    public void getAll(Blackhole blackhole) {
        for (Integer value : inputValues) {
            blackhole.consume(filledHashArrayList.get(value));
        }
    }
}
