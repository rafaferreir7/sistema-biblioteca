// @vitest-environment jsdom
import { describe, it, expect, vi } from 'vitest'
import { mount, flushPromises } from '@vue/test-utils'

import ExemplarLista from '../pages/ExemplarLista.vue'
import { api } from '../api/api.js'

vi.mock('../api/api.js', () => ({
  api: {
    get: vi.fn()
  }
}))

describe('ExemplarLista', () => {

  it('deve exibir os exemplares retornados pela API', async () => {

    api.get.mockResolvedValue({
      data: [
        {
          id: 1,
          codigoPatrimonio: 'EX001',
          disponivel: true,
          livro: {
            titulo: 'Java'
          },
          localizacao: {
            estante: 'A1',
            prateleira: 'P2'
          }
        }
      ]
    })

    const wrapper = mount(ExemplarLista)

    await flushPromises()

    expect(api.get).toHaveBeenCalledWith('/exemplares')

    expect(wrapper.text()).toContain('EX001')
    expect(wrapper.text()).toContain('Java')
    expect(wrapper.text()).toContain('A1')
    expect(wrapper.text()).toContain('P2')
    expect(wrapper.text()).toContain('Sim')
  })

})