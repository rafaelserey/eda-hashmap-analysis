import pandas as pd

df = pd.read_csv("probing_results.csv")

# Extrai benchmark base e métrica
df["BenchmarkBase"] = df["Benchmark"].str.split(":").str[0]
df["Metric"] = df["Benchmark"].str.split(":").str[1]

# Ajusta: onde não tem ":", é o Score principal
df["Metric"] = df["Metric"].fillna("Score")

# Agora pivot
tabela = df.pivot_table(
    index=["BenchmarkBase", "Param: loadFactor"],  # chave única
    columns="Metric",
    values="Score",   # queremos os valores
    aggfunc="first"
).reset_index()


tabela.to_csv("final_probing_results.csv")

