# Island Hopping

**Plataforma:** Kattis  
**Link do problema:** <https://open.kattis.com/problems/islandhopping>  
**Disciplina:** Resolução de Problemas com Grafos — T1AV3 (Grupo B)  
**Orientador:** Prof. Me. Ricardo Carubbi

---

## Integrantes do grupo

- Nathan Linhares Dias Malheiros
- Marcos Vinícius dos Santos e Silva
- Ana Julia Benevides Arraes Monteiro

---

## Linguagem

Java

---

## Como executar

**Compilar:**

```bash
javac src/Main.java src/PrimMST.java
```

**Executar com arquivo de entrada:**

```bash
java -cp src Main
```

O programa lê automaticamente de `dados/entrada.txt` se o arquivo existir. Caso contrário, lê da entrada padrão (`stdin`).

**Executar com entrada manual (stdin):**

```bash
java -cp src Main < dados/entrada.txt
```

---

## Modelagem como grafo ponderado

O problema pede a construção de pontes entre ilhas com custo mínimo total.

- **Vértices:** cada ilha é um vértice, identificado por sua posição `(x, y)` no plano.
- **Arestas:** o grafo é **completo** — toda par de ilhas pode ser conectado por uma ponte. As arestas são implícitas e não precisam ser armazenadas explicitamente.
- **Peso:** o custo de cada ponte é a **distância euclidiana** entre os dois pontos:
  `d(u, v) = √((x₁ − x₂)² + (y₁ − y₂)²)`

O objetivo é encontrar a **Árvore Geradora Mínima (MST)** desse grafo completo, cujo peso total corresponde ao comprimento mínimo de pontes necessário para conectar todas as ilhas.

---

## Algoritmo utilizado

**Prim com seleção linear** (`O(V²)`), implementado em [`src/PrimMST.java`](src/PrimMST.java).

O algoritmo parte de um vértice inicial (ilha 0) e cresce a árvore adicionando, a cada passo, a aresta de menor custo que conecta um vértice já incluído na MST a um ainda não visitado.

**Etapas principais:**

1. Inicializa `minDist[0] = 0` e `minDist[i] = ∞` para os demais vértices.
2. Seleciona o vértice `u` não visitado com menor `minDist`.
3. Marca `u` como visitado e acumula `minDist[u]` no custo total.
4. Para cada vértice `v` não visitado, atualiza `minDist[v]` com a distância euclidiana `dist(u, v)` se for menor que o valor atual.
5. Repete até todos os vértices serem visitados.

### Papel da escolha da próxima aresta (Prim sem heap)

A seleção do próximo vértice é feita por varredura linear no vetor `minDist` — escolhendo sempre o mínimo entre os não visitados. Essa abordagem é equivalente a usar uma fila de prioridade, mas com complexidade `O(V)` por iteração, o que resulta em `O(V²)` no total. Para grafos densos (como este, onde o número de arestas é `V²`), isso é tão eficiente quanto a versão com heap.

---

## Variação de MST

Nenhuma variação especial. É a MST clássica aplicada a um **grafo euclidiano completo implícito** (as arestas não são armazenadas — os pesos são calculados sob demanda).

---

## Análise de complexidade

| Componente | Complexidade |
| --- | --- |
| Tempo — Prim com seleção linear | `O(V²)` |
| Espaço — vetores `visited` e `minDist` | `O(V)` |
| Cálculo de distância por par | `O(1)` |

Como o grafo é completo (`E = V(V−1)/2`), `O(V²)` é ótimo para este caso.

---

## Casos especiais

- **Uma única ilha (`n = 1`):** nenhuma ponte é necessária; o custo é `0.0`. O algoritmo trata isso corretamente pois não há iterações de atualização.
- **Múltiplos casos de teste:** o programa lê `T` casos em sequência e acumula saída em um `StringBuilder` para eficiência.
- **Precisão numérica:** as coordenadas são `double` e a saída é formatada com 12 casas decimais (`%.12f`) usando `Locale.US` para evitar problemas com separador decimal.

---

## Evidência de submissão aceita

![Accepted](evidencia/accepted.png)
