<script setup>
import { ref, onMounted } from 'vue';
import { api } from '../api/api.js';

const exemplares = ref([]);
const carregando = ref(true);

const buscarExemplares = async () => {
  carregando.value = true;

  try {
    const resposta = await api.get('/exemplares');
    exemplares.value = resposta.data;
  } catch (erro) {
    console.error("Erro ao buscar exemplares:", erro);
  } finally {
    carregando.value = false;
  }
};

onMounted(() => {
  buscarExemplares();
});
</script>

<template>
  <div class="container my-4">

    <div class="d-flex justify-content-between align-items-center mb-3">
      <h2>Exemplares</h2>

      <button
        class="btn btn-outline-secondary"
        @click="buscarExemplares"
      >
        Atualizar
      </button>
    </div>

    <div v-if="carregando" class="text-center text-muted py-4">
      Carregando...
    </div>

    <div v-else class="table-responsive">
      <table class="table table-striped table-hover align-middle">

        <thead class="table-dark">
          <tr>
            <th>ID</th>
            <th>Código de patrimônio</th>
            <th>Disponível</th>
            <th>Livro</th>
            <th>Localização</th>
          </tr>
        </thead>

        <tbody>
          <tr
            v-for="exemplar in exemplares"
            :key="exemplar.id"
          >
            <td>{{ exemplar.id }}</td>

            <td>{{ exemplar.codigoPatrimonio }}</td>

            <td>
              <span v-if="exemplar.disponivel" class="badge bg-success">
                Sim
              </span>

              <span v-else class="badge bg-danger">
                Não
              </span>
            </td>

            <td>
              {{ exemplar.livro?.titulo || 'Não informado' }}
            </td>

            <td>
              Estante {{ exemplar.localizacao?.estante || '-' }}
              /
              Prateleira {{ exemplar.localizacao?.prateleira || '-' }}
            </td>
          </tr>

          <tr v-if="exemplares.length === 0">
            <td colspan="5" class="text-center text-muted">
              Nenhum exemplar encontrado.
            </td>
          </tr>
        </tbody>

      </table>
    </div>

  </div>
</template>