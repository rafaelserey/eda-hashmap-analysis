package benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.List;
import java.util.concurrent.TimeUnit;

import data_structures.hashstructures.QuadraticProbingHashMap;
import data_structures.io.reader.CsvReader;
								
@State(Scope.Thread)
@Fork(value = 5)
@Warmup(iterations = 5, time = 5)
@Measurement(iterations = 10, time = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)


public class QuadraticProbingHashMapBenchmark {

	@Param({"0.5","0.75","0.9"})
	private float loadFactor;

	private List<Integer> inputValues;
	private QuadraticProbingHashMap<Integer, Integer> quadraticProbingHash;
	private QuadraticProbingHashMap<Integer, Integer> filledQuadraticProbingHash;

   @Setup 
    public void setUp() {
    	inputValues = CsvReader.read("data/unsorted_data_alt.csv");
		quadraticProbingHash = new QuadraticProbingHashMap<Integer, Integer>(loadFactor);
		filledQuadraticProbingHash = new QuadraticProbingHashMap<Integer, Integer>(loadFactor);

		for(int value: inputValues) 
			filledQuadraticProbingHash.put(value, value);
	}

	@Benchmark
    public void putAll(Blackhole blackhole) {
        for (Integer value : inputValues) {
            quadraticProbingHash.put(value, value);
        }
    }

	@Benchmark
    public void getAll(Blackhole blackhole) {
        for (Integer value : inputValues) {
            blackhole.consume(filledQuadraticProbingHash.get(value));
        }
    }
}
