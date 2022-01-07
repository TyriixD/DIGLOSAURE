package ca.ulaval.glo4002.game;

import ca.ulaval.glo4002.game.api.breed.BreedApiEndPoint;
import ca.ulaval.glo4002.game.api.breed.assemblers.DinosaurBreedingDtoAssembler;
import ca.ulaval.glo4002.game.api.dinosaur.DinosaursApiEndPoint;
import ca.ulaval.glo4002.game.api.dinosaur.assemblers.DinosaurCreationDtoAssembler;
import ca.ulaval.glo4002.game.api.dinosaur.assemblers.DinosaurResponseAssembler;
import ca.ulaval.glo4002.game.api.dinosaur.assemblers.DinosaurWeightModificationDtoAssembler;
import ca.ulaval.glo4002.game.api.exceptions.mappers.ArmsTooShortExceptionMapper;
import ca.ulaval.glo4002.game.api.exceptions.mappers.CatchallExceptionMapper;
import ca.ulaval.glo4002.game.api.exceptions.mappers.DinosaurAlreadyParticipatingExceptionMapper;
import ca.ulaval.glo4002.game.api.exceptions.mappers.DuplicateNameExceptionMapper;
import ca.ulaval.glo4002.game.api.exceptions.mappers.IncompatibleDinosaurForBreedingExceptionMapper;
import ca.ulaval.glo4002.game.api.exceptions.mappers.InvalidBabyWeightChangeExceptionMapper;
import ca.ulaval.glo4002.game.api.exceptions.mappers.InvalidFatherExceptionMapper;
import ca.ulaval.glo4002.game.api.exceptions.mappers.InvalidGenderExceptionMapper;
import ca.ulaval.glo4002.game.api.exceptions.mappers.InvalidMotherExceptionMapper;
import ca.ulaval.glo4002.game.api.exceptions.mappers.InvalidResourceQuantityExceptionMapper;
import ca.ulaval.glo4002.game.api.exceptions.mappers.InvalidSpeciesExceptionMapper;
import ca.ulaval.glo4002.game.api.exceptions.mappers.InvalidWeightChangeExceptionMapper;
import ca.ulaval.glo4002.game.api.exceptions.mappers.InvalidWeightExceptionMapper;
import ca.ulaval.glo4002.game.api.exceptions.mappers.MaxCombatsReachedExceptionMapper;
import ca.ulaval.glo4002.game.api.exceptions.mappers.NoTurnToUnturnExceptionMapper;
import ca.ulaval.glo4002.game.api.exceptions.mappers.NonExistentNameExceptionMapper;
import ca.ulaval.glo4002.game.api.game.ResetGameApiEndPoint;
import ca.ulaval.glo4002.game.api.resources.ResourcesApiEndPoint;
import ca.ulaval.glo4002.game.api.resources.assemblers.ResourcesCreationDtoAssembler;
import ca.ulaval.glo4002.game.api.resources.assemblers.ResourcesResponseAssembler;
import ca.ulaval.glo4002.game.api.sumo.SumoDinoApiEndPoint;
import ca.ulaval.glo4002.game.api.sumo.assemblers.SumoFightCreationDtoAssembler;
import ca.ulaval.glo4002.game.api.sumo.assemblers.SumoFightResponseAssembler;
import ca.ulaval.glo4002.game.api.turn.TurnApiEndPoint;
import ca.ulaval.glo4002.game.api.turn.UnturnApiEndPoint;
import ca.ulaval.glo4002.game.api.turn.assemblers.TurnResponseAssembler;
import ca.ulaval.glo4002.game.application.breed.BreedDinosaursUseCase;
import ca.ulaval.glo4002.game.application.dinosaur.AddDinosaurUseCase;
import ca.ulaval.glo4002.game.application.dinosaur.GetAllDinosaursUseCase;
import ca.ulaval.glo4002.game.application.dinosaur.GetDinosaurByNameUseCase;
import ca.ulaval.glo4002.game.application.dinosaur.ModifyDinosaurWeightUseCase;
import ca.ulaval.glo4002.game.application.dinosaur.assemblers.DinosaurDtoAssembler;
import ca.ulaval.glo4002.game.application.game.ResetGameUseCase;
import ca.ulaval.glo4002.game.application.resources.AddResourcesUseCase;
import ca.ulaval.glo4002.game.application.resources.GetResourcesUseCase;
import ca.ulaval.glo4002.game.application.resources.assemblers.CategorizedResourcesDtoAssembler;
import ca.ulaval.glo4002.game.application.sumo.SumoFightDinosaurUseCase;
import ca.ulaval.glo4002.game.application.sumo.assemblers.SumoFightPredictionDtoAssembler;
import ca.ulaval.glo4002.game.application.turn.GameMemento;
import ca.ulaval.glo4002.game.application.turn.GameMementoImpl;
import ca.ulaval.glo4002.game.application.turn.TurnUseCase;
import ca.ulaval.glo4002.game.application.turn.assemblers.TurnDtoAssembler;
import ca.ulaval.glo4002.game.domain.breed.BreedingService;
import ca.ulaval.glo4002.game.domain.dinosaur.CarnivoreFeedingOrder;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurHerd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerbivoreFeedingOrder;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.game.Park;
import ca.ulaval.glo4002.game.domain.resources.EqualSplitRationStrategy;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.domain.sumo.SumoRing;
import ca.ulaval.glo4002.game.domain.turn.Turn;
import ca.ulaval.glo4002.game.infrastructure.external_service.breed.VeterinaryBreedingService;
import ca.ulaval.glo4002.game.infrastructure.external_service.breed.assemblers.BreedingRequestAssembler;
import ca.ulaval.glo4002.game.infrastructure.external_service.breed.assemblers.BreedingResponseAssembler;
import ca.ulaval.glo4002.game.infrastructure.persistence.game.GameRepositoryInMemory;
import ca.ulaval.glo4002.game.interfaces.configuration.CustomJsonProvider;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class GameServer implements Runnable {
    private static final int PORT = 8181;

    private BreedApiEndPoint breedApiEndPoint;
    private DinosaursApiEndPoint dinosaursApiEndPoint;
    private SumoDinoApiEndPoint sumoDinoApiEndPoint;
    private ResourcesApiEndPoint resourcesApiEndPoint;
    private TurnApiEndPoint turnApiEndPoint;
    private ResetGameApiEndPoint resetGameApiEndPoint;
    private UnturnApiEndPoint unturnApiEndPoint;

    public GameServer() {
        setupDependencies();
    }

    public static void main(String[] args) {
        new GameServer().run();
    }

    private void setupDependencies() {
        EqualSplitRationStrategy rationStrategy = new EqualSplitRationStrategy();
        Pantry pantry = new Pantry(rationStrategy);

        CarnivoreFeedingOrder carnivoreFeedingOrderStrategy = new CarnivoreFeedingOrder();
        HerbivoreFeedingOrder herbivoreFeedingOrderStrategy = new HerbivoreFeedingOrder();
        DinosaurHerd dinosaurHerd = new DinosaurHerd(carnivoreFeedingOrderStrategy, herbivoreFeedingOrderStrategy);

        SumoRing sumoRing = new SumoRing();

        Park park = new Park(pantry, dinosaurHerd, sumoRing);
        Turn turn = new Turn(park);

        Game game = new Game(park, turn);

        GameRepository gameRepository = new GameRepositoryInMemory();
        GameMemento gameMemento = new GameMementoImpl(gameRepository);
        gameRepository.save(game);

        createDinosaursDependencies(gameRepository,gameMemento);
        createSumoDinoDependencies(gameRepository);
        createResourcesDependencies(gameRepository);
        createExecuteTurnDependencies(gameRepository,gameMemento);
        createResetGameDependencies(gameRepository);


    }

    private void createDinosaursDependencies(GameRepository gameRepository,GameMemento gameMemento) {
        DinosaurFactory dinosaurFactory = new DinosaurFactory();

        DinosaurDtoAssembler dinosaurDtoAssembler = new DinosaurDtoAssembler();
        AddDinosaurUseCase addDinosaurUseCase = new AddDinosaurUseCase(gameRepository, dinosaurFactory,gameMemento);

        GetDinosaurByNameUseCase getDinosaurByNameUseCase = new GetDinosaurByNameUseCase(gameRepository,
                                                                                         dinosaurDtoAssembler);

        GetAllDinosaursUseCase getAllDinosaursUseCase = new GetAllDinosaursUseCase(gameRepository,
                                                                                   dinosaurDtoAssembler);
        ModifyDinosaurWeightUseCase modifyDinosaurWeightUseCase = new ModifyDinosaurWeightUseCase(gameRepository);

        DinosaurCreationDtoAssembler dinosaurCreationDtoAssembler = new DinosaurCreationDtoAssembler();
        DinosaurWeightModificationDtoAssembler dinosaurWeightModificationDtoAssembler = new DinosaurWeightModificationDtoAssembler();
        DinosaurResponseAssembler dinosaurResponseAssembler = new DinosaurResponseAssembler();
        dinosaursApiEndPoint = new DinosaursApiEndPoint(addDinosaurUseCase, getDinosaurByNameUseCase,
                                                        getAllDinosaursUseCase, dinosaurResponseAssembler,
                                                        dinosaurCreationDtoAssembler, modifyDinosaurWeightUseCase,
                                                        dinosaurWeightModificationDtoAssembler);

        createDinosaurBreedingDependencies(gameRepository, dinosaurFactory);
    }

    private void createDinosaurBreedingDependencies(GameRepository gameRepository, DinosaurFactory dinosaurFactory) {
        BreedingRequestAssembler breedingRequestAssembler = new BreedingRequestAssembler();
        BreedingResponseAssembler breedingResponseAssembler = new BreedingResponseAssembler();
        BreedingService breedingService = new VeterinaryBreedingService(breedingRequestAssembler,
                                                                        breedingResponseAssembler);

        BreedDinosaursUseCase dinosaurBreedingUseCase = new BreedDinosaursUseCase(gameRepository, dinosaurFactory,
                                                                                  breedingService);

        DinosaurBreedingDtoAssembler dinosaurBreedingDtoAssembler = new DinosaurBreedingDtoAssembler();
        breedApiEndPoint = new BreedApiEndPoint(dinosaurBreedingUseCase, dinosaurBreedingDtoAssembler);
    }

    private void createSumoDinoDependencies(GameRepository gameRepository) {
        SumoFightPredictionDtoAssembler sumoFightPredictionDtoAssembler = new SumoFightPredictionDtoAssembler();
        SumoFightResponseAssembler sumoFightResponseAssembler = new SumoFightResponseAssembler();
        SumoFightCreationDtoAssembler sumoFightCreationDtoAssembler = new SumoFightCreationDtoAssembler();

        SumoFightDinosaurUseCase sumoFightDinosaurUseCase = new SumoFightDinosaurUseCase(gameRepository,
                                                                                         sumoFightPredictionDtoAssembler);
        sumoDinoApiEndPoint = new SumoDinoApiEndPoint(sumoFightDinosaurUseCase, sumoFightCreationDtoAssembler,
                                                      sumoFightResponseAssembler);
    }

    private void createResourcesDependencies(GameRepository gameRepository) {
        CategorizedResourcesDtoAssembler categorizedResourcesDtoAssembler = new CategorizedResourcesDtoAssembler();
        GetResourcesUseCase getResourcesUseCase = new GetResourcesUseCase(gameRepository,
                                                                          categorizedResourcesDtoAssembler);

        ResourcesFactory resourcesFactory = new ResourcesFactory();
        AddResourcesUseCase addResourcesUseCase = new AddResourcesUseCase(gameRepository, resourcesFactory);

        ResourcesResponseAssembler resourceResponseAssembler = new ResourcesResponseAssembler();
        ResourcesCreationDtoAssembler resourcesCreationDtoAssembler = new ResourcesCreationDtoAssembler();

        resourcesApiEndPoint = new ResourcesApiEndPoint(getResourcesUseCase, addResourcesUseCase,
                                                        resourceResponseAssembler, resourcesCreationDtoAssembler);
    }

    private void createExecuteTurnDependencies(GameRepository gameRepository, GameMemento gameMemento) {
        TurnDtoAssembler turnDtoAssembler = new TurnDtoAssembler();
        TurnResponseAssembler turnResponseAssembler = new TurnResponseAssembler();

        TurnUseCase turnUseCase = new TurnUseCase(gameRepository, turnDtoAssembler,gameMemento);
        turnApiEndPoint = new TurnApiEndPoint(turnUseCase, turnResponseAssembler);
        createUnturnDependencies(turnUseCase,turnResponseAssembler);
    }
    private void createUnturnDependencies(TurnUseCase turnUseCase,TurnResponseAssembler turnResponseAssembler ) {
        unturnApiEndPoint = new UnturnApiEndPoint(turnUseCase,turnResponseAssembler);
    }


    private void createResetGameDependencies(GameRepository gameRepository) {
        ResetGameUseCase resetGameUseCase = new ResetGameUseCase(gameRepository);
        resetGameApiEndPoint = new ResetGameApiEndPoint(resetGameUseCase);
    }


    public void run() {
        Server server = new Server(PORT);
        ServletContextHandler contextHandler = new ServletContextHandler(server, "/");
        ResourceConfig packageConfig = ResourceConfig.forApplication(new Application() {
            @Override
            public Set<Object> getSingletons() {
                Set<Object> resources = new HashSet<>();

                addExceptionMappers(resources);
                addServerResources(resources);

                return resources;
            }
        });

        ServletContainer container = new ServletContainer(packageConfig);
        ServletHolder servletHolder = new ServletHolder(container);

        contextHandler.addServlet(servletHolder, "/*");

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (server.isRunning()) {
                server.destroy();
            }
        }
    }

    private void addExceptionMappers(Set<Object> resources) {
        resources.add(new CatchallExceptionMapper());
        resources.add(new DuplicateNameExceptionMapper());
        resources.add(new InvalidGenderExceptionMapper());
        resources.add(new InvalidResourceQuantityExceptionMapper());
        resources.add(new InvalidSpeciesExceptionMapper());
        resources.add(new NonExistentNameExceptionMapper());
        resources.add(new InvalidFatherExceptionMapper());
        resources.add(new InvalidMotherExceptionMapper());
        resources.add(new ArmsTooShortExceptionMapper());
        resources.add(new DinosaurAlreadyParticipatingExceptionMapper());
        resources.add(new MaxCombatsReachedExceptionMapper());
        resources.add(new InvalidWeightExceptionMapper());
        resources.add(new InvalidWeightChangeExceptionMapper());
        resources.add(new InvalidBabyWeightChangeExceptionMapper());
        resources.add(new NoTurnToUnturnExceptionMapper());
        resources.add(new IncompatibleDinosaurForBreedingExceptionMapper());
    }

    private void addServerResources(Set<Object> resources) {
        resources.add(dinosaursApiEndPoint);
        resources.add(breedApiEndPoint);
        resources.add(sumoDinoApiEndPoint);
        resources.add(resourcesApiEndPoint);
        resources.add(turnApiEndPoint);
        resources.add(resetGameApiEndPoint);
        resources.add(unturnApiEndPoint);

        resources.add(new CustomJsonProvider());
    }
}
