package saude.api.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saude.api.api.model.Exercicio;
import saude.api.api.model.Refeicao;
import saude.api.api.model.Sono;
import saude.api.api.repository.ExercicioRepository;
import saude.api.api.repository.RefeicaoRepository;
import saude.api.api.repository.SonoRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    @Autowired
    private RefeicaoRepository refeicaoRepository;

    @Autowired
    private SonoRepository sonoRepository;

    /**
     * Retorna a média de tempo gasto em todos os exercícios.
     *
     * @return a média de tempo de todos os registros da tabela de exercícios.
     */
    public Double getMediaTempoGeral() {
        List<Exercicio> exercicios = exercicioRepository.findAll();
        return exercicios.stream()
                .map(Exercicio::getTempo)
                .filter(Objects::nonNull)
                .mapToDouble(Number::doubleValue)
                .average()
                .orElse(0.0);
    }

    /**
     * Retorna os tempos individuais de todos os exercícios registrados.
     *
     * @return uma lista de tempos de cada exercício.
     */
    public List<Double> getTemposIndividuais() {
        List<Exercicio> exercicios = exercicioRepository.findAll();
        return exercicios.stream()
                .map(Exercicio::getTempo)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Retorna a média de calorias consumidas em todas as refeições.
     *
     * @return a média de calorias de todas as refeições registradas.
     */
    public Double getMediaCaloriasGeral() {
        List<Refeicao> refeicoes = refeicaoRepository.findAll();
        return refeicoes.stream()
                .map(Refeicao::getQuantidade)
                .filter(Objects::nonNull)
                .mapToDouble(Number::doubleValue)
                .average()
                .orElse(0.0);
    }

    /**
     * Retorna as calorias individuais de todas as refeições registradas.
     *
     * @return uma lista de calorias de cada refeição.
     */
    public List<Double> getCaloriasIndividuais() {
        List<Refeicao> refeicoes = refeicaoRepository.findAll();
        return refeicoes.stream()
                .map(Refeicao::getQuantidade)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Retorna a média de horas dormidas em todos os registros de sono.
     *
     * @return a média de horas dormidas de todos os registros.
     */
    public Double getMediaHorasSono() {
        List<Sono> sonos = sonoRepository.findAll();
        return sonos.stream()
                .mapToDouble(Sono::getHorasDormidas)
                .average()
                .orElse(0.0);
    }

    /**
     * Retorna as horas dormidas individuais de todos os registros de sono.
     *
     * @return uma lista de horas dormidas de cada registro.
     */
    public List<Integer> getHorasIndividuaisSono() {
        List<Sono> sonos = sonoRepository.findAll();
        return sonos.stream()
                .map(Sono::getHorasDormidas)
                .collect(Collectors.toList());
    }
}
