package chekingbasedonsf;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class CheckingSystemFunctionWhatBasedOn extends AbstractValidationRule {

	@Override
	public IStatus validate(IValidationContext ctx) {
		// TODO Auto-generated method stub
		//Получаем экземпляр абстрактного типа EObject, от которого порождены объекты CAPELLA
		EObject eObject = ctx.getTarget();
		//Проверяем какого типа есть объекты, производные от EObject
		//Нам интересны объекты SystemFunction
		if (eObject instanceof SystemFunction) {
			//Приведем типы
			SystemFunction currentSysFunc = (SystemFunction)eObject;
			//
			int iWhatIsRealized;
			//получим кол-во связей между системной функцией и операционными действиями
			iWhatIsRealized = currentSysFunc.getRealizedOperationalActivities().size();
			//если таких связей - нет, начинаем выяснять кто провинился
			if (iWhatIsRealized<=0) {
				if (currentSysFunc instanceof AbstractNamedElement) {
					AbstractNamedElement currentNamedElement = (AbstractNamedElement)currentSysFunc;
					//Если проверка выявила ошибку и проблемная системаная функция имеет имя
					return ctx.createFailureStatus(new Object [] {currentSysFunc.eClass().getName(), currentNamedElement.getName()});
				}
				//Если проверка выявила ошибку и проблемная системаная функция не имеет имени
				return ctx.createFailureStatus(new Object[] {currentSysFunc.eClass().getName(), "<без имени>"});
			}
		}
		//Если проверка не выявила ошибок
		return ctx.createSuccessStatus();
	}

}
