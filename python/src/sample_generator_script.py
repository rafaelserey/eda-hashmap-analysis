import csv
import random
import os

# Define o diretório de saída
dir_output = "data"
os.makedirs(dir_output, exist_ok=True)

# Semente para reprodutibilidade
random.seed(13)

def generate_csv(filename, total=1000000, min_value=1, max_value=100000000, allow_duplicates=False, sorted_file=False):
    """Gera dados sintéticos e salva em CSV."""
    
    range_size = max_value - min_value + 1
    
    if not allow_duplicates and total > range_size:
        raise ValueError(f"Cannot generate {total} unique values in the range [{min_value}, {max_value}]")
    
    if allow_duplicates:
        # Gera um subconjunto de números e replica
        base_numbers = [random.randint(min_value, max_value) for _ in range(total // 100)]
        numbers = []
        for num in base_numbers:
            numbers += [num] * 100
        numbers = numbers[:total]
    else:
        numbers = random.sample(range(min_value, max_value + 1), min(total, range_size))
    
    if sorted_file:
        numbers.sort()
    
    filepath = os.path.join(dir_output, filename)
    
    with open(filepath, 'w', newline='') as f:
        writer = csv.writer(f)
        writer.writerow(["Value"])
        for n in numbers:
            writer.writerow([n])
    
    return filepath

# Gera o CSV sem repetições e desordenado
output_file = generate_csv("unsorted_data_alt.csv", allow_duplicates=False, sorted_file=False)
print(f"CSV successfully saved at: {os.path.abspath(output_file)}")

